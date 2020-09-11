# School-Enrollment

Simple School Enrollment, a backend Rest API, using Java, Spring, Spring boot and m2 in memory DB.

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
  Lat: Double,
  Lon: Double,
  Grades : [
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







### Create a school
#### Request


```
POST http://localhost:8080/school
Accept: application/json
Content-Type: application/json

Payload:
{
  Lat: Double,
  Lon: Double,
  minimumGpa: Integer,
  maxNumberOfPupils: Integer
}
```

#### Response

```json
200 OK
id of the created school.
```



### Set friendship between two pupils
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

### Get All unwatched scheduled TV shows
#### Request

```
GET http://localhost:8080/api/tvSchedule/tvShows/unwatched
Accept: application/json
Content-Type: application/json
```

#### Response

```json
200 OK
[
    {
        "id": 1,
        "name": "Under the Dome",
        "image": "http://static.tvmaze.com/uploads/images/original_untouched/81/202627.jpg",
        "cast": [
            {
                "id": 7,
                "name": "Mackenzie Lintz",
                "image": "http://static.tvmaze.com/uploads/images/original_untouched/3/7816.jpg"
            }
        ],
        "firstUnwatchedEpisode": {
            "id": 1,
            "name": "Pilot",
            "season": 1,
            "number": 1,
            "airDate": "2013-06-24T00:00:00.000+0000"
        }
    }
]
```
```json
500 Internal Server Error
{
    "timestamp": "2020-09-05T17:19:22.723+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "Error While Trying To Connect To Db",
    "path": "/api/tvSchedule/tvShow"
}
```
### Mark an episode as watched
#### Request

```
PUT http://localhost:8080/api/tvSchedule/episode?id={id}
Accept: application/json
Content-Type: application/json
```

#### Response

```json
200 OK
```
```json
404 NOT FOUND
{
    "timestamp": "2020-09-05T18:28:03.706+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Tv Show Not found",
    "path": "/api/tvSchedule/tvShow"
}
```
```json
500 Internal Server Error
{
    "timestamp": "2020-09-05T17:19:22.723+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "Error While Trying To Connect To Db",
    "path": "/api/tvSchedule/tvShow"
}
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.




