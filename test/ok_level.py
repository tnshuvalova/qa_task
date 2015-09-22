import httplib2
import json
import requests

h = httplib2.Http()
h.follow_all_redirects = True

#1. Code that verifies that data is added to server;
url = "http://qainterview.cogniance.com/candidates"
headers = {'Content-Type': 'application/json'}
body = json.dumps({"name": "John", "position": "QA Intern"})
resp, content = h.request(url, "POST", body=body, headers=headers)
if content != None:
    print("POST request to http://qainterview.cogniance.com/candidates. Data is added to the server. Passed")
    id_created = json.loads(content.decode("utf-8"))["candidate"]["id"]
else:
    print("Data is not added to the server. Failed")
    id_created = 1

#2. Code that verifies correctness of returned response code for GET request.
url = "http://qainterview.cogniance.com/candidates"
resp, content = h.request(url, "GET")
if resp.status == 200:
    print("GET request to http://qainterview.cogniance.com/candidates has response status 200. Passed")
else:
    print("GET request to http://qainterview.cogniance.com/candidates does not have response status 200. Failed")

#3. Code that verifies correctness of returned response code for GET request with id specified.
url = "http://qainterview.cogniance.com/candidates/1"
resp, content = h.request(url, "GET")
if resp.status == 200:
    print("GET request to http://qainterview.cogniance.com/candidates has response status 200. Passed")
else:
    print("GET request to http://qainterview.cogniance.com/candidates does not have response status 200. Failed")

#4. Code that verifies correctness of returned response code for POST request and candidate is added successfully.
url = "http://qainterview.cogniance.com/candidates"
headers = {'Content-Type': 'application/json'}
body = json.dumps({"name": "John", "position": "QA Intern"})
resp, content = h.request(url, "POST", body=body, headers=headers)
if resp.status == 201:
    print("POST request to http://qainterview.cogniance.com/candidates has response status 201. Passed")
else:
    print("POST request to http://qainterview.cogniance.com/candidates does not have response status 201. Failed")

#5. Code that verifies correctness of returned response code for POST request when header Content-Type is not sent. 400 error is expected.
url = "http://qainterview.cogniance.com/candidates"
body = json.dumps({"name": "John", "position": "QA Intern"})
resp, content = h.request(url, "POST", body=body)
if resp.status == 400:
    print("POST request to http://qainterview.cogniance.com/candidates has response status 400 (header Content-Type is not sent). Passed")
else:
    print("POST request to http://qainterview.cogniance.com/candidates does not have response status 400 (header Content-Type is not sent). Failed")

#6. Code that verifies correctness of returned response code for POST request when name in body is not sent. 400 error is expected.
url = "http://qainterview.cogniance.com/candidates"
headers = {'Content-Type': 'application/json'}
body = json.dumps({"position": "QA Intern"})
resp, content = h.request(url, "POST", body=body, headers=headers)
if resp.status == 400:
    print("POST request to http://qainterview.cogniance.com/candidates has response status 400 (name in body is not sent). Passed")
else:
    print("POST request to http://qainterview.cogniance.com/candidates does not have response status 400 (name in body is not sent). Failed")


#7. Code that verifies correctness of returned response code for DELETE request.
url = "http://qainterview.cogniance.com/candidates/" + str(id_created)
resp = requests.delete(url)
if resp.status_code == 200:
    print("DELETE request to " + url + " has response status 200. Passed")
else:
    print("DELETE request to " + url + " does not have response status 200. Failed")
