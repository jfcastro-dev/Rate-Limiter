# Rate-Limiter-Service
A rate limiter service that will use spring boot &amp; redis

### How it Works
This is a dummy Spring boot application. An interceptor will check if a 
given IP Address has exceeded the amount of requests that are allowed
within a time window.

#### Why IP Addresses?
Simply put - it was easier. Realistically, you may want to record something like the session id associated with
a bearer token.

#### How does this scale in a distributed system?
Quite well, actually. We run into this problem any time a system scales -
that is, any time a new instance spins up, the cache will be recreated in a new instance.
This is why we host redis in a separate container - in this case, it is passed to the actual Spring Boot Application.

If deployed with Kubernetes - the Redis container will need to be kept in a separate pod from the application,
as we want the Redis container to act as a singleton. Please note that it would require some adaptation to actually deploy
this with K8s, but that is outside the scope of this mini-project.

#### How to Run
To Run this application, you must first have the necessary dependencies installed - JDK 17, Maven & Docker.
From there, run the Docker Image for Redis, set the REDIS_HOST environment variable to the host URL of your 
redis container, then build & run the Java application. There is a sample page that runs at the 
root URL to test it - it will return 429 after 10 requests in 5 minutes.