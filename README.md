# spring_recap
some spring basic skills for recap!

### dependencies
- web
- lombok
- jpa
- mysql

```bash
dependencies {
	implementation 'mysql:mysql-connector-java:8.0.33'
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	compileOnly 'org.projectlombok:lombok'
	annotationProcessor 'org.projectlombok:lombok'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}
```

### jdbcTemplate
```java
// jdbctemplate은 datasource를 주입 받아서 생성
private final JdbcTemplate jdbcTemplate;

public ItemRepositoryImpl(DataSource dataSource) {
  this.jdbcTemplate = new JdbcTemplate(dataSource);
}
```
[CRUD Example](https://github.com/totohoon02/spring_recap/blob/main/ItemRepositoryImpl.java)

### JPA
- method name based inference
- extends JpaRepository(entity, pk)
- ORM
[CRUD Example](https://github.com/totohoon02/spring_recap/blob/main/MemberService.java)
