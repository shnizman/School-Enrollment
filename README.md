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

### Enroll a pupil to a school

### Calculation
Pupil will be enrolled to a school that maximizes the following formula:  
NUMBER OF FRIENDS IN THE SCHOOL * 1 / DISTANCE FROM SCHOOL(KM)

Distance is calculated using [Haversine formula](https://en.wikipedia.org/wiki/Haversine_formula)

### Prerequisite
In order to enroll a pupil to a specific school, the pupil GPA should be greater than schools
minimum GPA and the number of enrolled pupils has to be less than the schools max number of pupils

#### Request

```
GET http://localhost:8080/enroll/{pupilId}
Accept: application/json
Content-Type: application/json
```

#### Response

```json
200 OK
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.
