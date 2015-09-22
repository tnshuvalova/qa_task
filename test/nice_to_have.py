import httplib2
import json
import requests

h = httplib2.Http()
h.follow_all_redirects = True

#1. Code that verifies correctness of GET method for all candidates.
url = "http://qainterview.cogniance.com/candidates"
resp, content = h.request(url, "GET")
if resp.status == 200:
    print("GET request to http://qainterview.cogniance.com/candidates has response status 200. Passed")
    content_decoded = json.loads(content.decode("utf-8"))
    if "candidates" in content_decoded:
        print("GET request to http://qainterview.cogniance.com/candidates. List of candidates is returned. Passed")
    else:
        print("GET request to http://qainterview.cogniance.com/candidates. List of candidates is not returned. Failed")
else:
    print("GET request to http://qainterview.cogniance.com/candidates does not have response status 200. Failed")

#2. Code that verifies correctness of POST method and GET method for particular candidate.
#verify response: candidate is added properly
url = "http://qainterview.cogniance.com/candidates"
headers = {'Content-Type': 'application/json'}
body = json.dumps({"name": "Helen", "position": "QA Intern"})
resp, content = h.request(url, "POST", body=body, headers=headers)
if content != None and "candidate" in json.loads(content.decode("utf-8")):
    print("POST request to http://qainterview.cogniance.com/candidates. Data is added to the server. Passed")
    candidate_created = json.loads(content.decode("utf-8"))["candidate"]
    id_created = candidate_created["id"]
    if candidate_created["name"] == "Helen" and candidate_created["position"] == "QA Intern":
        print("POST request to http://qainterview.cogniance.com/candidates. New candidate is returned. Passed")
    else:
        print("POST request to http://qainterview.cogniance.com/candidates. Wrong candidate added. Failed")
    #verify response for GET request for candidate
    resp, content = h.request(url+"/"+str(id_created), "GET")
    if content != None and "candidate" in json.loads(content.decode("utf-8")):
        print("POST request to http://qainterview.cogniance.com/candidates. Data is added to the server. Passed")
        candidate_created = json.loads(content.decode("utf-8"))["candidate"]
        id_created = candidate_created["id"]
    if candidate_created["name"] == "Helen" and candidate_created["position"] == "QA Intern":
        print("GET request to http://qainterview.cogniance.com/candidates. New candidate is returned. Passed")
    else:
        print("GET request to http://qainterview.cogniance.com/candidates. Wrong candidate returned. Failed")
else:
    print("Data is not added to the server. Failed")

#3. Code that verifies GET method for candidate that doesn't exist.
#verify response: candidate is added properly
url = "http://qainterview.cogniance.com/candidates"
headers = {'Content-Type': 'application/json'}
body = json.dumps({"name": "Helen", "position": "QA Intern"})
resp, content = h.request(url, "POST", body=body, headers=headers)
candidate_created = json.loads(content.decode("utf-8"))["candidate"]
id_created = candidate_created["id"]
resp, content = h.request(url+"/"+str(id_created+1), "GET")
if str(resp.status).find("4") == 0:
    print("GET request to http://qainterview.cogniance.com/candidates. Candidate doesn't exist. Passed")
else:
    print("GET request to http://qainterview.cogniance.com/candidates. Wrong candidate returned. Failed")

#4. Code that verifies unsuccessfull POST method.
url = "http://qainterview.cogniance.com/candidates"
headers = {'Content-Type': 'application/json'}
#verify response when header is omitted
body = json.dumps({"name": "Helen", "position": "QA Intern"})
resp, content = h.request(url, "POST", body=body)
if "error" in content.decode("utf-8"):
    response_decoded = content.decode("utf-8")
    if response_decoded.find("Make sure your Content-Type header is application/json!") != -1:
        print("POST request to http://qainterview.cogniance.com/candidates. Content-Type header is omitted. Error returned. Description is correct. Passed")
    else:
        print("POST request to http://qainterview.cogniance.com/candidates. Content-Type header is omitted. Error returned. Description is not correct. Failed")
else:
    print("POST request to http://qainterview.cogniance.com/candidates. Content-Type header is omitted. Error is not returned. Failed")
#verify response when name is omitted
body = json.dumps({"position": "QA Intern"})
resp, content = h.request(url, "POST", body=body, headers=headers)
if "error" in content.decode("utf-8"):
    response_decoded = content.decode("utf-8")
    if response_decoded.find("Name is absent in your request body!") != -1:
        print("POST request to http://qainterview.cogniance.com/candidates. Name is omitted. Error returned. Description is correct. Passed")
    else:
        print("POST request to http://qainterview.cogniance.com/candidates. Name is omitted. Error returned. Description is not correct. Failed")
else:
    print("POST request to http://qainterview.cogniance.com/candidates. Name is omitted. Error is not returned. Failed")
#verify response when position is omitted. Must be ok
body = json.dumps({"name": "Julia"})
resp, content = h.request(url, "POST", body=body, headers=headers)
if content != None and "candidate" in json.loads(content.decode("utf-8")):
    print("POST request to http://qainterview.cogniance.com/candidates. Data is added to the server. Passed")
    candidate_created = json.loads(content.decode("utf-8"))["candidate"]
    id_created = candidate_created["id"]
    if candidate_created["name"] == "Julia" and candidate_created["position"] == "":
        print("POST request to http://qainterview.cogniance.com/candidates. New candidate is returned. Passed")
    else:
        print("POST request to http://qainterview.cogniance.com/candidates. Wrong candidate added. Failed")
    #verify response for GET request for candidate
    resp, content = h.request(url+"/"+str(id_created), "GET")
    if content != None and "candidate" in json.loads(content.decode("utf-8")):
        print("POST request to http://qainterview.cogniance.com/candidates. Data is added to the server. Passed")
        candidate_created = json.loads(content.decode("utf-8"))["candidate"]
        id_created = candidate_created["id"]
    if candidate_created["name"] == "Julia" and candidate_created["position"] == "":
        print("GET request to http://qainterview.cogniance.com/candidates. New candidate is returned. Passed")
    else:
        print("GET request to http://qainterview.cogniance.com/candidates. Wrong candidate returned. Failed")
else:
    print("Data is not added to the server. Failed")

#5. Code that verifies correctness of DELETE method.
url = "http://qainterview.cogniance.com/candidates"
headers = {'Content-Type': 'application/json'}
body = json.dumps({"name": "James", "position": "QA Intern"})
resp, content = h.request(url, "POST", body=body, headers=headers)
if content != None and "candidate" in json.loads(content.decode("utf-8")):
    print("POST request to http://qainterview.cogniance.com/candidates. Data is added to the server. Passed")
    candidate_created = json.loads(content.decode("utf-8"))["candidate"]
    id_created = candidate_created["id"]
    if candidate_created["name"] == "James" and candidate_created["position"] == "QA Intern":
        print("POST request to http://qainterview.cogniance.com/candidates. New candidate is returned. Passed")
    else:
        print("POST request to http://qainterview.cogniance.com/candidates. Wrong candidate added. Failed")
    #verify DELETE request for candidate
    resp = requests.delete(url)
    if resp.status_code == 200:
        print("DELETE request to http://qainterview.cogniance.com/candidates for candidate "+str(id_created)+". Have 200 response. Passed")
        candidate_created = json.loads(content.decode("utf-8"))["candidate"]
        id_created = candidate_created["id"]
        #verify negative response for GET request for candidate deleted
        resp, content = h.request(url+"/"+str(id_created), "GET")
        if resp.status_code == 400:
            print("GET request to http://qainterview.cogniance.com/candidates for deleted candidate "+str(id_created)+". Have 400 response. Passed")
        elif resp.status_code == 200:
            print("GET request to http://qainterview.cogniance.com/candidates for deleted candidate "+str(id_created)+". Have 200 response. Failed")
        else:
            print("GET request to http://qainterview.cogniance.com/candidates for deleted candidate "+str(id_created)+". Failed")
    else:
        print("DELETE request to http://qainterview.cogniance.com/candidates. Failed")
else:
    print("Data is not added to the server. Failed")


