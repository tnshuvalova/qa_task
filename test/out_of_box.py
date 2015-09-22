import unittest
import redis
import json
import httplib2
import requests

SUCCESS = 200
SUCCESS_ADDED = 201
FAILURE = 400
NOT_FOUND = 404

class Candidate:
    @staticmethod
    def get_candidate_from_db_by_key(connection, key):
        candidate_keys = connection.hkeys(key)
        candidate_keys = [key.decode("utf-8") for key in candidate_keys]
        candidate_values = connection.hvals(key)
        candidate_values = [value.decode("utf-8") for value in candidate_values]
        candidate = dict(zip(candidate_keys, candidate_values))
        return candidate

class RedisConnection:
    @staticmethod
    def initiate_connection():
        r = redis.StrictRedis(host="localhost", port=6379, db=15)
        r.flushdb()
        return r

class CandidateTest(unittest.TestCase):
    def setUp(self):
        #set up redis connection
        self.r = redis.StrictRedis(host="localhost", port=6379, db=15)
        self.r.flushdb()

    def test_candidate_by_key(self):
        candidate_james = {"name": "James", "position": "QA intern"}
        self.r.hmset("candidate:james", candidate_james)
        self.assertDictEqual(candidate_james, Candidate.get_candidate_from_db_by_key(self.r, "candidate:james"))

class RedisConnectionTest(unittest.TestCase):

    def setUp(self):
        self.r = RedisConnection.initiate_connection()

    def test_redis_connection(self):
        self.assertIsInstance(self.r, redis.StrictRedis)

    def test_data_added_to_db(self):
        self.r.hmset("candidate:james", {"name": "James", "position": "QA intern"})
        self.r.hmset("candidate:jacob_no_position", {"name": "Jacob"})
        self.assertEqual(self.r.hget("candidate:james", "name"), b"James")
        self.assertEqual(self.r.hget("candidate:jacob_no_position", "name"), b"Jacob")

class ResponseCodeTest(unittest.TestCase):
    def setUp(self):
        #set up data
        self.r = RedisConnection.initiate_connection()
        self.r.hmset("candidate:james", {"name": "James", "position": "QA intern"})
        self.r.hmset("candidate:lorens", {"name": "Lorence", "position": "QA intern"})
        self.r.hmset("candidate:lara", {"name": "Lara", "position": "RM intern"})
        self.r.hmset("candidate:goodwill", {"name": "Goodwill", "position": "QA intern"})
        self.r.hmset("candidate:nat", {"name": "Nat", "position": "FE intern"})
        self.r.hmset("candidate:mikael", {"name": "Mikael", "position": "RM intern"})
        self.r.hmset("candidate:jacob_no_position", {"name": "Jacob"})
        self.r.hmset("candidate:unknown_name", {"position": "QA intern"})
        #set up request parameters
        self.url = "http://qainterview.cogniance.com/candidates"
        self.headers = {'Content-Type': 'application/json'}
        self.headers_wrong = {'Content-Type': 'application/xml'}
        #set up http object
        self.h = httplib2.Http()
        self.h.follow_all_redirects = True

    def test_get_response_code_success(self):
        resp, content = self.h.request(self.url, "GET")
        self.assertEqual(resp.status, SUCCESS, "GET request to http://qainterview.cogniance.com/candidates does not have response status 200. Failed")

    def test_get_specified_candidate_response_code_success(self):
        #create candidate with known id
        candidate = json.dumps(Candidate.get_candidate_from_db_by_key(self.r, "candidate:james"))
        resp, content = self.h.request(self.url, "POST", body=candidate, headers=self.headers)
        id_created = json.loads(content.decode("utf-8"))["candidate"]["id"]
        #make request with id given above
        resp, content = self.h.request(self.url+"/"+str(id_created), "GET")
        self.assertEqual(resp.status, SUCCESS, "GET request to http://qainterview.cogniance.com/candidates for the existing candidate with id "+ str(id_created) +" does not have response status 200. Failed")

    def test_get_non_exist_candidate_code_failure(self):
        #create candidate with known id
        candidate = json.dumps(Candidate.get_candidate_from_db_by_key(self.r, "candidate:james"))
        resp, content = self.h.request(self.url, "POST", body=candidate, headers=self.headers)
        id_created = json.loads(content.decode("utf-8"))["candidate"]["id"]
        #make request with id given above
        resp, content = self.h.request(self.url+"/"+str(id_created+1), "GET")
        self.assertEqual(resp.status, NOT_FOUND, "GET request to http://qainterview.cogniance.com/candidates for the non-existing candidate with id "+ str(id_created+1) +" does not have response status 400. Failed")

    def test_post_response_code_success(self):
        candidate = json.dumps(Candidate.get_candidate_from_db_by_key(self.r, "candidate:lara"))
        resp, content = self.h.request(self.url, "POST", body=candidate, headers=self.headers)
        self.assertEqual(resp.status, SUCCESS_ADDED, "POST request to http://qainterview.cogniance.com/candidates does not have response status 201. Failed")

    def test_post_no_header_response_code_error(self):
        candidate = json.dumps(Candidate.get_candidate_from_db_by_key(self.r, "candidate:lara"))
        resp, content = self.h.request(self.url, "POST", body=candidate)
        self.assertEqual(resp.status, FAILURE, "POST request to http://qainterview.cogniance.com/candidates does not have response status 400 (header Content-Type is not sent). Failed")

    def test_post_wrong_header_response_code_error(self):
        candidate = json.dumps(Candidate.get_candidate_from_db_by_key(self.r, "candidate:lara"))
        resp, content = self.h.request(self.url, "POST", body=candidate, headers = self.headers_wrong)
        self.assertEqual(resp.status, FAILURE, "POST request to http://qainterview.cogniance.com/candidates does not have response status 400 (header Content-Type is not application/json). Failed")

    def test_post_no_name_candidate_response_code_error(self):
        candidate = json.dumps(Candidate.get_candidate_from_db_by_key(self.r, "candidate:unknown_name"))
        resp, content = self.h.request(self.url, "POST", body=candidate, headers = self.headers_wrong)
        self.assertEqual(resp.status, FAILURE, "POST request to http://qainterview.cogniance.com/candidates does not have response status 400 (candidate has no name). Failed")

    def test_delete_existing_candidate_code_success(self):
        #create candidate with known id
        candidate = json.dumps(Candidate.get_candidate_from_db_by_key(self.r, "candidate:james"))
        resp, content = self.h.request(self.url, "POST", body=candidate, headers=self.headers)
        id_created = json.loads(content.decode("utf-8"))["candidate"]["id"]
        #make delete request with id given above
        resp = requests.delete(self.url + "/" + str(id_created))
        self.assertEqual(resp.status_code, SUCCESS, "DELETE request to http://qainterview.cogniance.com/candidates for candidate "+str(id_created)+" does not have response status 200. Failed")

    def test_delete_non_existing_candidate_code_error(self):
        #create candidate with known id
        candidate = json.dumps(Candidate.get_candidate_from_db_by_key(self.r, "candidate:james"))
        resp, content = self.h.request(self.url, "POST", body=candidate, headers=self.headers)
        id_created = json.loads(content.decode("utf-8"))["candidate"]["id"]
        #make delete request with id given above
        resp = requests.delete(self.url + "/" + str(id_created+1))
        self.assertEqual(resp.status_code, FAILURE, "DELETE request to http://qainterview.cogniance.com/candidates for non-existing candidate "+str(id_created+1)+" does not have response status 400. Failed")

class ServerMethodsTest(unittest.TestCase):
    def setUp(self):
        #set up data
        self.r = RedisConnection.initiate_connection()
        self.r.hmset("candidate:james", {"name": "James", "position": "QA intern"})
        self.r.hmset("candidate:lorens", {"name": "Lorence", "position": "QA intern"})
        self.r.hmset("candidate:lara", {"name": "Lara", "position": "RM intern"})
        self.r.hmset("candidate:goodwill", {"name": "Goodwill", "position": "QA intern"})
        self.r.hmset("candidate:nat", {"name": "Nat", "position": "FE intern"})
        self.r.hmset("candidate:mikael", {"name": "Mikael", "position": "RM intern"})
        self.r.hmset("candidate:jacob_no_position", {"name": "Jacob"})
        self.r.hmset("candidate:unknown_name", {"position": "QA intern"})
        #set up request parameters
        self.url = "http://qainterview.cogniance.com/candidates"
        self.headers = {'Content-Type': 'application/json'}
        self.headers_wrong = {'Content-Type': 'application/xml'}
        #set up http object
        self.h = httplib2.Http()
        self.h.follow_all_redirects = True

    def test_post_data_added(self):
        candidate = json.dumps(Candidate.get_candidate_from_db_by_key(self.r, "candidate:james"))
        resp, content = self.h.request(self.url, "POST", body=candidate, headers=self.headers)
        self.assertIsNotNone(content, "Data is not added to the server. Failed")

    def test_post_data_not_added_no_name(self):
        candidate = json.dumps(Candidate.get_candidate_from_db_by_key(self.r, "candidate:unknown_name"))
        resp, content = self.h.request(self.url, "POST", body=candidate, headers=self.headers)
        self.assertTrue(content.decode("utf-8").find("error") != -1, "Candidate without name is added to the server. Failed")

    def test_post_data_not_added_no_header(self):
        candidate = json.dumps(Candidate.get_candidate_from_db_by_key(self.r, "candidate:lorens"))
        resp, content = self.h.request(self.url, "POST", body=candidate)
        self.assertTrue(content.decode("utf-8").find("error") != -1, "No header Content-Type passed. Candidate is added to the server. Failed")

    def test_post_data_not_added_wrong_header(self):
        candidate = json.dumps(Candidate.get_candidate_from_db_by_key(self.r, "candidate:lorens"))
        resp, content = self.h.request(self.url, "POST", body=candidate, headers=self.headers_wrong)
        self.assertTrue(content.decode("utf-8").find("error") != -1, "Header Content-Type has wrong value. Candidate is added to the server. Failed")

    def test_get_all_candidates_success(self):
        resp, content = self.h.request(self.url, "GET")
        content_decoded = json.loads(content.decode("utf-8"))
        self.assertIn("candidates", content_decoded, "GET request to http://qainterview.cogniance.com/candidates. List of candidates is not returned. Failed")

    def test_post_new_candidate_get_this_candidate_success(self):
        candidate = json.dumps(Candidate.get_candidate_from_db_by_key(self.r, "candidate:goodwill"))
        print(self.headers)
        headers = {"Content-Type": "application/json"}
        resp, content = self.h.request(self.url, "POST", body=candidate, headers=headers)
        content_decoded = json.loads(content.decode("utf-8"))
        self.assertIn("candidate", content_decoded, "POST request to http://qainterview.cogniance.com/candidates. Candidate is not loaded. Failed")
        #verify candidate in response. id is not known, skip it
        id_created = content_decoded["candidate"]["id"]
        del content_decoded["candidate"]["id"]
        self.assertDictEqual(content_decoded["candidate"], json.loads(candidate))
        #make get request for the candidate loaded
        resp, content = self.h.request(self.url + "/" + str(id_created), "GET")
        content_decoded = json.loads(content.decode("utf-8"))
        #id is known, add it to initial candidate
        candidate = json.loads(candidate)
        candidate["id"] = id_created
        self.assertDictEqual(candidate, content_decoded["candidate"], "GET request to http://qainterview.cogniance.com/candidates. Wrong candidate is returned. Failed")

    def test_get_non_exist_candidate_failure(self):
        #create candidate with known id
        candidate = json.dumps(Candidate.get_candidate_from_db_by_key(self.r, "candidate:james"))
        resp, content = self.h.request(self.url, "POST", body=candidate, headers=self.headers)
        id_created = json.loads(content.decode("utf-8"))["candidate"]["id"]
        #make request with id given above
        resp, content = self.h.request(self.url+"/"+str(id_created+1), "GET")
        self.assertIn("error", json.loads(content.decode("utf-8")), "GET request to http://qainterview.cogniance.com/candidates. Wrong candidate returned. Failed")

    def test_post_wrong_header_message_error(self):
        candidate = json.dumps(Candidate.get_candidate_from_db_by_key(self.r, "candidate:mikael"))
        resp, content = self.h.request(self.url, "POST", body=candidate)
        self.assertIn("error", json.loads(content.decode("utf-8")), "POST request to http://qainterview.cogniance.com/candidates. No header but no error message")
        self.assertTrue(json.loads(content.decode("utf-8"))["error"].find("Make sure your Content-Type header is application/json"), "POST request to http://qainterview.cogniance.com/candidates. Content-Type header is omitted. Error returned. Description is not correct. Failed")
        resp, content = self.h.request(self.url, "POST", body=candidate, headers=self.headers_wrong)
        self.assertTrue(json.loads(content.decode("utf-8"))["error"].find("Make sure your Content-Type header is application/json"), "POST request to http://qainterview.cogniance.com/candidates. Content-Type header is wrong. Error returned. Description is not correct. Failed")

    def test_post_no_name_message_error(self):
        candidate = json.dumps(Candidate.get_candidate_from_db_by_key(self.r, "candidate:mikael"))
        resp, content = self.h.request(self.url, "POST", body=candidate)
        self.assertIn("error", json.loads(content.decode("utf-8")), "POST request to http://qainterview.cogniance.com/candidates. No name but no error message")
        self.assertTrue(json.loads(content.decode("utf-8"))["error"].find("Name is absent in your request body"), "POST request to http://qainterview.cogniance.com/candidates. Name is omitted. Wrong message. Failed")

    def test_post_no_position_get_success(self):
        candidate = json.dumps(Candidate.get_candidate_from_db_by_key(self.r, "candidate:jacob_no_position"))
        resp, content = self.h.request(self.url, "POST", body=candidate, headers=self.headers)
        self.assertIn("candidate", json.loads(content.decode("utf-8")), "POST request to http://qainterview.cogniance.com/candidates. No position but candidate is not loaded. Failure")
        #verify that GET request is OK for this candidate
        id_created = json.loads(content.decode("utf-8"))["candidate"]["id"]
        resp, content = self.h.request(self.url + "/" + str(id_created), "GET")
        candidate = json.loads(candidate)
        candidate["id"] = id_created
        self.assertDictEqual(candidate, json.loads(content.decode("utf-8"))["candidate"], "GET request to http://qainterview.cogniance.com/candidates. No position. Wrong candidate is returned. Failure")

    def test_delete_existing_success(self):
        candidate = json.dumps(Candidate.get_candidate_from_db_by_key(self.r, "candidate:jacob_no_position"))
        resp, content = self.h.request(self.url, "POST", body=candidate, headers=self.headers)
        #verify that candidate exists
        id_created = json.loads(content.decode("utf-8"))["candidate"]["id"]
        resp, content = self.h.request(self.url + "/" + str(id_created), "GET")
        self.assertEqual(resp.status, SUCCESS, "GET request to http://qainterview.cogniance.com/candidates/" + str(id_created) + ". No candidate is returned. Failure")
        #delete that candidate
        resp = requests.delete(self.url + "/" + str(id_created))
        #try to get deleted candidate
        resp, content = self.h.request(self.url + "/" + str(id_created), "GET")
        content_decoded = json.loads(content.decode("utf-8"))
        self.assertIn("error", content_decoded, "GET request to http://qainterview.cogniance.com/candidates/" + str(id_created) + ". Candidate doesn't exist but is returned. Failure")

    def test_delete_non_existing_error(self):
        candidate = json.dumps(Candidate.get_candidate_from_db_by_key(self.r, "candidate:jacob_no_position"))
        resp, content = self.h.request(self.url, "POST", body=candidate, headers=self.headers)
        #verify that candidate exists
        id_created = json.loads(content.decode("utf-8"))["candidate"]["id"]
        #delete that candidate
        resp = requests.delete(self.url + "/" + str(id_created+1))
        content_decoded = resp.content.decode("utf-8")
        self.assertTrue(content_decoded.find("error") != -1, "GET request to http://qainterview.cogniance.com/candidates/" + str(id_created) + ". Candidate doesn't exist but no error when deleted. Failure")

if __name__ == "__main__":
    unittest.main()