#springboot

 ##quickstart
  ###create maven project 
    1.useing maven project using maven-archetype-quickstart 
    2.import parent project in pom
    3.create main-function annotationed with @SpringBootApplication
    4.import spring-boot-maven-plugin plugin in pom

    =================project structrue====================
    src/main/java is srource folder
    test/main/java is test folder  
    pom.xml is maven configration  
    ======================================================
    
 ##web project 
    1.import spring-boot-starter-web dependence in pom 
    2.create controller annotationed with @RestController
    3.coding restfull interface  annotationed with @RequestMapping
    4.mvn package
    5.run project using commond 'java -jar springboot-1.0-SNAP SHOT.jar'
    6.curl 'http://127.0.0.1:8080/index'
    
  ##junit test
    1.import spring-boot-starter-test dependence in pom 
    2.create test class in src/test folder annotationed with @RunWith @SpringBootTest @AutoConfigureMockMvc
    3.coding test function
    =================project structrue====================
    Mock with MockMvc to initiate the request, which is actually a class method call, then execute the controller, using Mockito's mock to mock the return of the underlying data during the execution, and finally validate the result.
    @RunWith is used for running test
    @SpringBootTest is used for statementing springboot test
    @AutoConfigureMockMvc is used for autowiring beans
    ======================================================
    