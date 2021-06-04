# sdk-scala
Sdk for live video streaming with Sidemash Cloud in Scala 

## Installation
This SDK is set to work with Scala 2.10, 2.11, 2.12 and 2.13
In your `build.sbt`, add this : 
```scala
libraryDependencies += "com.sidemash" %% "sdk-scala" % "2.0.0"
```

## Configuration
First, log in your account and create an `AuthAccess` object to query Sidemash Cloud with API. On creation, you will have a token and a privateKey : Use them to initialize a Sidemash client.
```scala 
import com.sidemash.SidemashClient
import com.sidemash.Auth

val sdm = SidemashClient(Auth(token = "1234", privateKey ="secret"))
```

## Usage 
### Nomenclature 
The is pretty staright forward, if You have a resource that you want to Get List Update Patch or Delete, the you should do `sdm.{resourceTypeCamelCase}.{operation}({operationArgs})`.


### Get resources
```scala
sdm.streamSquare.get(id = "1234")
```

### List resources
```scala 
sdm.streamSquare.list(where = Some("createdTime:in:[Yesterday.14h, Yesterday.15h["))
```
```scala 
import com.sidemash.form.ListStreamSquareForm
sdm.streamSquare.list(ListStreamSquareForm(orderBy = Some("createdTime:ASC,status:DESC")))
```

### Create resources
```scala
sdm.streamSquare.create(size = StreamSquare.Size.S,isElastic = false)
```
```scala
import com.sidemash.model.StreamSquare
import com.sidemash.form.CreateStreamSquareForm

sdm.streamSquare.create(CreateStreamSquareForm(size = StreamSquare.Size.S, isElastic = false))
```

### Update resources
```scala 
sdm.streamSquare.update(id = "1234", newSize = Some(StreamSquare.Size.M))
```
```scala 
import com.sidemash.form.UpdateStreamSquareForm

sdm.streamSquare.update(UpdateStreamSquareForm(id = "1234", newSize = Some(StreamSquare.Size.M)))
```

### Delete resources 
```scala
sdm.streamSquare.delete(id = "1234")
```


