My akka-http study project.

Dependencies
------------
- akka-http-experimental_2.11
- akka-http-spray-json-experimental

Runnig
------
<pre><code>sbt run</code></pre>

Usage
-----

Get people:
<pre><code>curl http://localhost:8080/person</code></pre>

Get person by id:
<pre><code>curl http://localhost:8080/person/1</code></pre>

Post a new person:
<pre><code>curl -H "Content-Type: application/json" -X POST -d '{"id": 4, "name": "Johny Cage", "age": 44, "address": "New York" }' http://localhost:8080/person</code></pre>
