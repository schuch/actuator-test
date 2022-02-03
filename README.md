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
2022-02-03 15:58:33.012  INFO 80212 --- [           main] com.example.demo.DemoApplication         : Started DemoApplication in 2.338 seconds (JVM running for 4.188)
2022-02-03 15:58:34.213  INFO 80212 --- [1)-10.180.42.16] com.example.demo.TestHealthIndicator     : created TestHealthIndicator for 1
2022-02-03 15:58:34.214  INFO 80212 --- [1)-10.180.42.16] com.example.demo.TestHealthIndicator     : created TestHealthIndicator for 2
2022-02-03 15:58:34.218  INFO 80212 --- [2)-10.180.42.16] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2022-02-03 15:58:34.218  INFO 80212 --- [2)-10.180.42.16] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2022-02-03 15:58:34.220  INFO 80212 --- [2)-10.180.42.16] o.s.web.servlet.DispatcherServlet        : Completed initialization in 2 ms
2022-02-03 15:58:40.298  INFO 80212 --- [nio-8080-exec-1] com.example.demo.TestHealthIndicator     : created TestHealthIndicator for 1
2022-02-03 15:58:40.298  INFO 80212 --- [nio-8080-exec-1] com.example.demo.TestHealthIndicator     : created TestHealthIndicator for 2
2022-02-03 15:58:41.223  INFO 80212 --- [nio-8080-exec-2] com.example.demo.TestHealthIndicator     : created TestHealthIndicator for 1
2022-02-03 15:58:41.224  INFO 80212 --- [nio-8080-exec-2] com.example.demo.TestHealthIndicator     : created TestHealthIndicator for 2
2022-02-03 15:58:42.289  INFO 80212 --- [nio-8080-exec-3] com.example.demo.TestHealthIndicator     : created TestHealthIndicator for 1
2022-02-03 15:58:42.289  INFO 80212 --- [nio-8080-exec-3] com.example.demo.TestHealthIndicator     : created TestHealthIndicator for 2
```

Non aggregated health indicator instances are reused.
