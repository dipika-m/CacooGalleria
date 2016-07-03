# cacooApi-spring-oAuth1.a
These are the Spring Security OAuth (1a) sample apps 

This app consumes  external resources (e.g. Cacoo), and the precise external resource it consumes has
been chosen to show the use of the relevant protocol.

The `Cacoo` app is a online diagram management You can create online diagram with real time collaboration.
`Cacoo` is the easiest way to create shareable diagrams online but it doesn't know what you have to do with your diagrams ? . 
Thats where `consumer` comes in i.e `cacooApi` created using `spring` . 

You go to `CacooGalleria` to browse the photos that are stored in `cacoo` and
"print" them (this feature is not actually implemented).  
The `CacooGalleria` app has to get your permission to access the photos, but only for read
access - this is the key separation of concerns that is offered by
OAuth protocols: `Cacoo` is able to ask the user to authorize `CacooGalleria`
to read  photos for the purpose of printing them.

To run the apps the easiest thing is to first install all the
artifacts using `mvn install`  and run `mvn tomcat7:run`. 

Visit `http://localhost:8080/nulab` in a browser and go to the
`cacoo` tab.  The result should be:

* You are prompted to authenticate with `CacooGalleria` (the login screen tells
  you the users available and their passwords)
  ![alt text](https://github.com/dipikam86/CacooGalleria/blob/master/images/cacooLogin.png "cacooLogin")
  
* The correct authorization is not yet in place for `CacooGalleria` to access
  your photos on `cacoo` on your behalf, so `CacooGalleria` redirects your
  browser to the `cacoo` UI to get the authorization.

* You are prompted to authenticate with `Cacoo`.

![alt text](https://github.com/dipikam86/CacooGalleria/blob/master/images/cacooSignIn.png "cacooSignIn")

* Then `Cacoo` will ask you if you authorize `CacooGalleria` to access your
  photos.
![alt text](https://github.com/dipikam86/CacooGalleria/blob/master/images/cacooAuth.png "cacooAuth")  

* If you say "yes" then your browser will be redirected back to `CacooGalleria`
  and this time the correct authorization is present, so you will be
  able to see your photos.
  
![alt text](https://github.com/dipikam86/CacooGalleria/blob/master/images/cacooDiagram.png "cacooDiagram")
## How to build the WAR files

Use Maven (2.2.1 works) and, from this directory do 

    $ mvn package


## How to deploy in Eclipse (e.g. STS)

* Ensure the Spring Security OAuth dependencies are available locally
first.  You can do this by importing all projects, or by building on
the command line before importing the samples (using `mvn install`).

* Import the projects:

        File->Import...->Maven->Existing Maven Projects->Next

  browse to the parent directory containing all the
  samples and press `Finish`.
  
