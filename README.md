# School-Enrollment

Simple School Enrollment, a backend Rest API, using Java, Spring, Spring boot and m2 in memory DB.  
Edge cases are not been handled


## Installation

To run the application type

mvn spring-boot:run


mvn install
If you are using maven, please refer to the mvn repository and choose your desired package version.

## Usage
There is no Pupil without at least one Grade

### Create a pupil
#### Request


```
POST http://localhost:8080/pupil
Accept: application/json
Content-Type: application/json

Payload:
{
  lat: Double,
  lon: Double,
  grades : [
      {
          courseName : String,
          grade : Integer
      }
  ]
}
```



#### Response

```json
200 OK
id of the created pupil
```

```json
404 Not Found
{
    "timestamp": "2020-09-11T08:40:57.648+0000",
    "status": 404,
    "error": "Not Found",
    "message": "No message available",
    "path": "/pupil"
}
```

```json
400 Bad Request
{
    "timestamp": "2020-09-11T08:42:52.938+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "Pupil must have at least one grade",
    "path": "/pupil"
}
```

```json
500 Internal Server Error
{
    "timestamp": "2020-09-11T09:00:25.857+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "Error While Trying To Save New Pupil to Db",
    "path": "/pupil"
}
```

### Create a school
#### Request


```
POST http://localhost:8080/school
Accept: application/json
Content-Type: application/json

Payload:
{
  lat: Double,
  lon: Double,
  minimumGpa: Integer,
  maxNumberOfPupils: Integer
}
```

#### Response

```json
200 OK
id of the created school.
```

```json
400 Bad Request
{
    "timestamp": "2020-09-11T09:33:19.950+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "JSON parse error",
    "path": "/school"
}
```


### Set friendship between two pupils

This relation is symmetric

#### Request

```
POST http://localhost:8080/setFriendShip/{firstPupilId}/{secondPupilId}
Accept: application/json
Content-Type: application/json
```

#### Response

```json
200 OK

```

```json
404 Not Found
{
    "timestamp": "2020-09-11T09:36:28.118+0000",
    "status": 404,
    "error": "Not Found",
    "message": "pupil Id Not found",
    "path": "/setFriendShip/45/68"
}
```

### Enroll a pupil to a school

#### Calculation
Pupil will be enrolled to a school that maximizes the following formula:  
NUMBER OF FRIENDS IN THE SCHOOL * 1 / DISTANCE FROM SCHOOL(KM)

DISTANCE FROM SCHOOL - is calculated using [Haversine formula](https://en.wikipedia.org/wiki/Haversine_formula)

#### Prerequisite
In order to enroll a pupil to a specific school, the pupil GPA should be greater than schools
minimum GPA and the number of enrolled pupils has to be less than the schools max number of pupils

#### Request

```
POST http://localhost:8080/enroll/{pupilId}
Accept: application/json
Content-Type: application/json
```

#### Response

```json
200 OK
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.
