



# Software Engineering Course project template

## Setup dev environment
* Grab Ubuntu 16.04 based VM
* Clone projects
```
  > cd ~
  > mkdir projects
  > cd projects
  > git clone https://github.com/swe-course/saas-template.git
```
* Install prerequisites, Prepare local env configuration files
```
  > cd saas-template
  > sudo ./prereq.sh
  > cp .env.template .env
  > cp settings.xml.template settings.xml
```
* build & run
```
  > ./build.sh
  > sudo ./up.sh -d
```

## API endpoints

| Endpoint | Details |
| --- | --- |
| /application.wadl | **Get XML based API description [GET]**:<br/>curl http://\<host\>:\<port\>/application.wadl<br/><hr/>Not Allowed - OPTIONS, HEAD, POST, PUT, PATCH, DELETE, TRACE |
| /healthcheck | **Health check [GET]**:<br/>curl http://\<host\>:\<port\>/healthcheck<br/><hr/>Not Allowed - OPTIONS, HEAD, POST, PUT, PATCH, DELETE, TRACE |
| /contacts | **Get/Find contacts [GET]**:<br/>curl http://\<host\>:\<port\>/api/v1/contacts<br/>curl http://\<host\>:\<port\>/api/v1/contacts?firstName=John&email=.\*unknown.com<br/><hr/>Not Allowed - OPTIONS, HEAD, POST, PUT, PATCH, DELETE, TRACE |
| /contacts/{uuid} | **Get contact by Id [GET]**:<br/>curl http://\<host\>:\<port\>/api/v1/contacts/1<br/><br/>**Create contact [POST]**:<br/>curl -H "Content-Type: application/json" -d '{"email":"first_name.last_name@gmail.com","firstName":"first_name","lastName":"last_name"}' http://\<host\>:\<port\>/api/v1/contacts<br/><br/>**Update contact [PUT]**:<br/>curl --request PUT -H "Content-Type: application/json" -d '{"email":"elom_musk@gmail.com","firstName":"Elon","lastName":"Musk"}' http://\<host\>:\<port\>/api/v1/contacts/2<br/><br/>**Patch contact [PATCH]**:<br/>curl --request PATCH -H "Content-Type: application/json" -d '{"email":"elom.musk@gmail.com"}' http://\<host\>:\<port\>/api/v1/contacts/2<br/><br/>**Delete contact**:<br/>curl --request DELETE http://\<host\>:\<port\>/api/v1/contacts/2<br/><hr/>Not Allowed - OPTIONS, HEAD, TRACE |
