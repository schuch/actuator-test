# actuator-test

Spring Boot aggregates multiple instances of the same health indicator

```
{
  "status": "UP",
  "components": {
    "diskSpace": {
      "status": "UP"
    },
    "ping": {
      "status": "UP"
    },
    "single": {
      "status": "UP"
    },
    "test": {
      "status": "UP",
      "components": {
        "createTestBean1": {
          "status": "UP"
        },
        "createTestBean2": {
          "status": "UP"
        }
      }
    }
  }
}
```

The weird thing is: Every aggregated health indicator gets recreated every time the health endpoint is pulled.

```
2022-02-03 16:14:22.972  INFO 27776 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1857 ms
2022-02-03 16:14:23.174  INFO 27776 --- [           main] com.example.demo.SingleHealthIndicator   : created SingleHealthIndicator
2022-02-03 16:14:23.197  INFO 27776 --- [           main] com.example.demo.TestHealthIndicator     : created TestHealthIndicator for 1
2022-02-03 16:14:23.199  INFO 27776 --- [           main] com.example.demo.TestHealthIndicator     : created TestHealthIndicator for 2
2022-02-03 16:14:23.801  INFO 27776 --- [           main] o.s.b.a.e.web.EndpointLinksResolver      : Exposing 1 endpoint(s) beneath base path '/actuator'
2022-02-03 16:14:23.849  INFO 27776 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2022-02-03 16:14:23.868  INFO 27776 --- [           main] com.example.demo.DemoApplication         : Started DemoApplication in 3.43 seconds (JVM running for 5.703)
2022-02-03 16:14:25.243  INFO 27776 --- [1)-10.180.42.16] com.example.demo.TestHealthIndicator     : created TestHealthIndicator for 1
2022-02-03 16:14:25.244  INFO 27776 --- [1)-10.180.42.16] com.example.demo.TestHealthIndicator     : created TestHealthIndicator for 2
2022-02-03 16:14:25.248  INFO 27776 --- [2)-10.180.42.16] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2022-02-03 16:14:25.249  INFO 27776 --- [2)-10.180.42.16] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2022-02-03 16:14:25.251  INFO 27776 --- [2)-10.180.42.16] o.s.web.servlet.DispatcherServlet        : Completed initialization in 2 ms
2022-02-03 16:14:46.794  INFO 27776 --- [nio-8080-exec-1] com.example.demo.TestHealthIndicator     : created TestHealthIndicator for 1
2022-02-03 16:14:46.794  INFO 27776 --- [nio-8080-exec-1] com.example.demo.TestHealthIndicator     : created TestHealthIndicator for 2
2022-02-03 16:14:48.749  INFO 27776 --- [nio-8080-exec-2] com.example.demo.TestHealthIndicator     : created TestHealthIndicator for 1
2022-02-03 16:14:48.749  INFO 27776 --- [nio-8080-exec-2] com.example.demo.TestHealthIndicator     : created TestHealthIndicator for 2
2022-02-03 16:14:49.693  INFO 27776 --- [nio-8080-exec-3] com.example.demo.TestHealthIndicator     : created TestHealthIndicator for 1
2022-02-03 16:14:49.694  INFO 27776 --- [nio-8080-exec-3] com.example.demo.TestHealthIndicator     : created TestHealthIndicator for 2
2022-02-03 16:14:51.387  INFO 27776 --- [nio-8080-exec-4] com.example.demo.TestHealthIndicator     : created TestHealthIndicator for 1
2022-02-03 16:14:51.387  INFO 27776 --- [nio-8080-exec-4] com.example.demo.TestHealthIndicator     : created TestHealthIndicator for 2
2022-02-03 16:14:52.051  INFO 27776 --- [nio-8080-exec-5] com.example.demo.TestHealthIndicator     : created TestHealthIndicator for 1
2022-02-03 16:14:52.051  INFO 27776 --- [nio-8080-exec-5] com.example.demo.TestHealthIndicator     : created TestHealthIndicator for 2
```

Non aggregated health indicator instances are reused.

Is this behavior intended?

