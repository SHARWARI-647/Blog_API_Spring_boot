# 📝 Full-Stack Blog Application

A **full-stack Blog Application** built using **Spring Boot, React.js, Supabase PostgreSQL, JWT Authentication, and Supabase Storage**.

The application provides a complete blogging platform with secure authentication, blog CRUD operations, bookmarks, comments, likes, profile management, image uploads, and an admin dashboard.

---

## 🚀 Overview

This project follows a layered full-stack architecture:

* ⚙️ **Spring Boot REST API** — Backend
* ⚛️ **React.js** — Frontend
* 🐘 **Supabase PostgreSQL** — Database
* 🔐 **Spring Security + JWT** — Authentication & Authorization
* 🖼️ **Supabase Storage** — Image/File Storage
* 📚 **Swagger/OpenAPI** — API Documentation
* 🧪 **JUnit 5 + Mockito + Spring Boot Test** — Backend Testing
* 📮 **Postman** — API Testing

Users can register and authenticate securely, create and manage blog posts, like and bookmark blogs, add comments, manage their profiles, and upload images.

---

# ✨ Features

## 👤 User Authentication

* User registration
* User login
* JWT-based authentication
* Access token and refresh token
* Password encryption
* Role-based authorization
* Protected REST APIs

## 📝 Blog Management

* Create blog posts
* View all blogs
* View individual blogs
* Update blogs
* Delete blogs
* Like blogs
* Track blog views
* Blog categories
* Blog creation and update timestamps
* Ownership-based authorization

## 🔖 Bookmark System

* Add bookmarks
* View bookmarked blogs
* Remove bookmarks

## 💬 Comment System

* Add comments
* View comments
* Delete comments

## 👤 Profile Management

* View user profile
* Update profile information
* Update profile image
* User bio and contact information

## 🖼️ File & Image Upload

* Upload blog images
* Upload profile images
* Store images in Supabase Storage
* Store image URLs in PostgreSQL

## 👮 Authorization

* JWT token validation
* Protected endpoints
* Authenticated user operations
* Blog ownership validation
* Role-based authorization

## 📊 Admin Dashboard

* Admin dashboard
* Blog statistics
* User/blog management
* Blog analytics

---

# 🛠️ Tech Stack

## Frontend

| Technology   | Purpose             |
| ------------ | ------------------- |
| React.js     | User interface      |
| JavaScript   | Application logic   |
| HTML5        | Structure           |
| CSS3         | Styling             |
| Axios        | API communication   |
| React Router | Client-side routing |

## Backend

| Technology      | Purpose                        |
| --------------- | ------------------------------ |
| Java            | Programming language           |
| Spring Boot     | Backend framework              |
| Spring Web      | REST APIs                      |
| Spring Data JPA | Data access                    |
| Hibernate       | ORM                            |
| Spring Security | Authentication & authorization |
| JWT             | Token-based authentication     |
| Swagger/OpenAPI | API documentation              |

## Database & Storage

| Technology          | Purpose             |
| ------------------- | ------------------- |
| Supabase PostgreSQL | Relational database |
| PostgreSQL          | Database            |
| Supabase Storage    | Image/file storage  |

## Testing

| Technology           | Purpose                    |
| -------------------- | -------------------------- |
| JUnit 5              | Unit testing               |
| Mockito              | Mocking dependencies       |
| Spring Boot Test     | Spring application testing |
| MockMvc              | REST controller testing    |
| `@WebMvcTest`        | Controller-layer testing   |
| `@DataJpaTest`       | Repository testing         |
| `@SpringBootTest`    | Integration testing        |
| Spring Security Test | Security testing           |
| Postman              | API testing                |

## Development Tools

* Git
* GitHub
* Postman
* Swagger UI
* VS Code
* IntelliJ IDEA / Eclipse
* Maven

---

# 📁 Project Structure

## Backend — Spring Boot

```text
Blog_API_Spring_boot/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── ...
│   │   │       ├── controller/
│   │   │       ├── service/
│   │   │       ├── repository/
│   │   │       ├── model/
│   │   │       ├── dto/
│   │   │       ├── security/
│   │   │       └── config/
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/
│       └── java/
│           └── ...
│
├── pom.xml
└── README.md
```

## Frontend — React

```text
blog-frontend/
│
├── src/
│   ├── components/
│   ├── pages/
│   ├── services/
│   ├── assets/
│   ├── App.jsx
│   └── main.jsx
│
├── public/
├── package.json
└── README.md
```

---

# 📦 Installation & Setup

## 1. Clone the Repositories

Clone the backend and frontend repositories:

```bash
git clone <backend-repository-url>
git clone <frontend-repository-url>
```

---

# ⚙️ Backend Setup

## 2. Navigate to Backend

```bash
cd Blog_API_Spring_boot
```

---

## 3. Configure Supabase PostgreSQL

Create a project in Supabase and obtain the PostgreSQL database credentials.

Configure:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:postgresql://<HOST>:<PORT>/<DATABASE>
spring.datasource.username=<USERNAME>
spring.datasource.password=<PASSWORD>

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

> ⚠️ Never commit database passwords, JWT secrets, or Supabase service keys to GitHub.

---

## 4. Configure Supabase Storage

Create a Supabase Storage bucket for application images.

Example configuration:

```properties
supabase.url=<SUPABASE_URL>
supabase.bucket=<BUCKET_NAME>
supabase.service-key=<SUPABASE_SERVICE_KEY>
```

Keep the service key private.

For production, sensitive configuration should preferably be supplied through environment variables or a secure secrets manager.

---

## 5. Run Backend

Using Maven:

```bash
mvn spring-boot:run
```

Or run the main Spring Boot application from IntelliJ IDEA/Eclipse.

Backend:

```text
http://localhost:8080
```

---

# ⚛️ Frontend Setup

## 6. Navigate to Frontend

```bash
cd blog-frontend
```

## 7. Install Dependencies

```bash
npm install
```

## 8. Start React Application

```bash
npm run dev
```

Frontend:

```text
http://localhost:5173
```

---

# 🔌 REST API Endpoints

## 👤 Authentication

### Register

```http
POST /auth/register
```

Request:

```json
{
  "userName": "user",
  "email": "user@example.com",
  "password": "secret"
}
```

### Login

```http
POST /auth/login
```

Request:

```json
{
  "email": "user@example.com",
  "password": "secret"
}
```

The login response contains the JWT access token.

---

# 📝 Blog APIs

### Create Blog

```http
POST /blogs
```

Requires authentication.

Example:

```json
{
  "title": "My First Blog",
  "description": "This is my first blog post.",
  "category": "Technology"
}
```

### Get All Blogs

```http
GET /blogs
```

### Get Blog by ID

```http
GET /blogs/{id}
```

### Update Blog

```http
PUT /blogs/{id}
```

Only the blog owner can update the blog.

### Delete Blog

```http
DELETE /blogs/{id}
```

Only the blog owner can delete the blog.

### Like Blog

```http
PUT /blogs/like/{id}
```

---

# 🔖 Bookmark APIs

### Add Bookmark

```http
POST /bookmark/{blogId}
```

### Get Bookmarks

```http
GET /bookmark/getbookmark
```

### Remove Bookmark

```http
DELETE /bookmark/{blogId}
```

---

# 💬 Comment APIs

### Add Comment

```http
POST /comment
```

### Get Comments

```http
GET /comment/{blogId}
```

### Delete Comment

```http
DELETE /comment/{commentId}
```

---

# 👤 User APIs

### Get Profile

```http
GET /user/profile
```

### Update Profile

```http
PUT /user/profile
```

---

# 🖼️ File Upload

Blog and profile images are uploaded through the Spring Boot backend.

```http
POST /upload
```

Request type:

```text
multipart/form-data
```

The backend uploads the image to Supabase Storage and uses the resulting image URL in the application.

---

# 🔐 Authentication Flow

```text
┌───────────────┐
│ Register User │
└───────┬───────┘
        ↓
┌───────────────┐
│  Login User   │
└───────┬───────┘
        ↓
┌─────────────────────┐
│ Generate JWT Token  │
└─────────┬───────────┘
          ↓
┌─────────────────────┐
│ React Stores Token  │
└─────────┬───────────┘
          ↓
┌──────────────────────────┐
│ Axios Sends Bearer Token │
└──────────┬───────────────┘
           ↓
┌──────────────────────────┐
│ Spring Security Validates│
│          JWT             │
└──────────┬───────────────┘
           ↓
┌──────────────────────────┐
│ Access Protected API     │
└──────────────────────────┘
```

Protected requests use:

```http
Authorization: Bearer <JWT_TOKEN>
```

---

# 📚 Swagger / OpenAPI

The application includes Swagger/OpenAPI documentation.

After starting the backend, open:

```text
http://localhost:8080/swagger-ui/index.html
```

Swagger allows you to:

* View all REST endpoints
* View request/response models
* Test APIs
* Test protected endpoints
* Provide JWT authorization
* Inspect API documentation

---

# 🧪 Testing

The backend uses the **Spring Boot testing ecosystem** to test controllers, services, repositories, security, and application integration.

## Testing Technologies

* JUnit 5
* Mockito
* Spring Boot Test
* MockMvc
* `@WebMvcTest`
* `@DataJpaTest`
* `@SpringBootTest`
* Spring Security Test
* Assertions
* Mockito verification

---

## 🏗️ Testing Architecture

```text
                    JUnit 5
                       │
        ┌──────────────┼──────────────┐
        ↓              ↓              ↓
   Controller       Service       Repository
        │              │              │
     MockMvc        Mockito       @DataJpaTest
        │              │              │
        ↓              ↓              ↓
      REST API     Business Logic   JPA/Database
        │              │              │
        └──────────────┴──────────────┘
                       ↓
              Integration Testing
                @SpringBootTest
```

---

# 1. Controller Testing

Controllers are tested using:

* JUnit 5
* `@WebMvcTest`
* MockMvc
* Mockito

Controller tests verify:

* HTTP methods
* Request handling
* Request validation
* HTTP status codes
* JSON responses
* Service interaction
* Authentication behavior

Example:

```java
@WebMvcTest(BlogController.class)
class BlogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BlogService blogService;

    @Test
    void shouldGetAllBlogs() throws Exception {

        when(blogService.getAllBlogs())
                .thenReturn(List.of());

        mockMvc.perform(get("/blogs"))
                .andExpect(status().isOk());

        verify(blogService).getAllBlogs();
    }
}
```

> If the project uses an older Spring Boot version, `@MockBean` can be used instead of `@MockitoBean`.

### Controller Test Cases

| Test Case               | Expected Result          |
| ----------------------- | ------------------------ |
| Get all blogs           | `200 OK`                 |
| Get blog by valid ID    | `200 OK`                 |
| Blog not found          | `404 Not Found`          |
| Create blog             | `200 OK` / `201 Created` |
| Invalid request         | `400 Bad Request`        |
| No authentication       | `401 Unauthorized`       |
| Insufficient permission | `403 Forbidden`          |
| Update blog             | Successful response      |
| Delete blog             | Successful response      |

---

# 2. Service Layer Testing

Service classes are tested independently using **JUnit 5 and Mockito**.

The repository is mocked so that the actual database is not required.

Example:

```java
@ExtendWith(MockitoExtension.class)
class BlogServiceTest {

    @Mock
    private BlogRepository blogRepository;

    @InjectMocks
    private BlogService blogService;

    @Test
    void shouldGetBlogById() {

        Blog blog = new Blog();
        blog.setId(1L);
        blog.setTitle("My Blog");

        when(blogRepository.findById(1L))
                .thenReturn(Optional.of(blog));

        Blog result = blogService.getBlogById(1L);

        assertNotNull(result);
        assertEquals("My Blog", result.getTitle());

        verify(blogRepository).findById(1L);
    }
}
```

### Service Test Cases

* Create blog
* Get all blogs
* Get blog by ID
* Blog not found
* Update blog
* Delete blog
* Ownership validation
* Like blog
* Add bookmark
* Add comment
* Profile operations
* Authentication logic
* Exception handling

---

# 3. Mockito Testing

Mockito isolates a class from its dependencies.

For example:

```text
BlogService
     ↓
BlogRepository
```

The actual repository is replaced with a mock:

```java
@Mock
private BlogRepository blogRepository;
```

Define expected behavior:

```java
when(blogRepository.findById(1L))
        .thenReturn(Optional.of(blog));
```

Verify interactions:

```java
verify(blogRepository).findById(1L);
```

Mockito also verifies that unwanted operations do not happen:

```java
verify(blogRepository, never())
        .delete(any(Blog.class));
```

This is useful for ownership authorization.

---

# 4. Repository Testing

Repository testing uses:

```java
@DataJpaTest
```

It verifies:

* Entity mappings
* JPA queries
* Repository methods
* CRUD operations
* Relationships

Example:

```java
@DataJpaTest
class BlogRepositoryTest {

    @Autowired
    private BlogRepository blogRepository;

    @Test
    void shouldSaveBlog() {

        Blog blog = new Blog();
        blog.setTitle("Test Blog");
        blog.setDescription("Testing Blog");

        Blog savedBlog = blogRepository.save(blog);

        assertNotNull(savedBlog.getId());
        assertEquals("Test Blog", savedBlog.getTitle());
    }
}
```

---

# 5. MockMvc Testing

MockMvc tests REST endpoints without requiring a real HTTP server.

Example:

```java
mockMvc.perform(get("/blogs"))
        .andExpect(status().isOk());
```

Testing a POST request:

```java
mockMvc.perform(
        post("/blogs")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "title": "Test Blog",
                        "description": "Testing Blog"
                    }
                    """)
)
.andExpect(status().isOk());
```

MockMvc verifies the complete controller request/response behavior.

---

# 6. JWT Security Testing

Protected endpoints are tested with different authentication scenarios.

### Valid JWT

```text
Valid JWT
   ↓
Request accepted
   ↓
200 OK
```

### Missing JWT

```text
No JWT
   ↓
401 Unauthorized
```

### Invalid JWT

```text
Invalid JWT
   ↓
401 Unauthorized
```

### Insufficient Permission

```text
Valid JWT
   ↓
Insufficient Permission
   ↓
403 Forbidden
```

Spring Security Test can also be used to simulate authenticated users during controller tests.

---

# 7. Ownership Authorization Testing

The application verifies blog ownership before update and delete operations.

```text
User A
   ↓
Creates Blog #1
```

User A:

```text
PUT /blogs/1
      ↓
   Allowed
```

User B:

```text
PUT /blogs/1
      ↓
   Forbidden
```

The same ownership rule applies to deletion.

This behavior is tested at both the service and controller levels.

---

# 8. Exception Testing

JUnit assertions are used to verify expected exceptions.

Example:

```java
@Test
void shouldThrowExceptionWhenBlogNotFound() {

    when(blogRepository.findById(100L))
            .thenReturn(Optional.empty());

    assertThrows(
            ResourceNotFoundException.class,
            () -> blogService.getBlogById(100L)
    );
}
```

Test scenarios include:

* Resource not found
* Unauthorized access
* Invalid request
* Duplicate email/user
* Invalid authentication
* Invalid blog ID
* Validation errors

---

# 9. Mockito Verification

Mockito verifies interactions between services and repositories.

Verify a method was called:

```java
verify(blogRepository).save(any(Blog.class));
```

Verify a method was not called:

```java
verify(blogRepository, never())
        .delete(any(Blog.class));
```

For unauthorized operations:

```text
Unauthorized User
       ↓
Service rejects operation
       ↓
Repository delete()
       ↓
Must NOT be called
```

---

# 🔟 Testing Different Layers

| Layer             | Testing Technology   | Purpose                      |
| ----------------- | -------------------- | ---------------------------- |
| Controller        | `@WebMvcTest`        | Controller isolation         |
| Controller        | MockMvc              | REST request/response        |
| Service           | JUnit 5              | Business logic               |
| Service           | Mockito              | Mock dependencies            |
| Repository        | `@DataJpaTest`       | JPA/database testing         |
| Security          | Spring Security Test | JWT/security behavior        |
| Integration       | `@SpringBootTest`    | Complete application context |
| API               | Postman              | Manual API testing           |
| API Documentation | Swagger              | API verification             |

---

# 1️⃣1️⃣ Integration Testing

The complete Spring Boot application can be tested using:

```java
@SpringBootTest
class BlogApplicationIntegrationTest {

    @Test
    void applicationContextLoads() {
    }
}
```

This loads the Spring application context and verifies that the required components and configurations can be initialized.

Integration tests can be used to verify interactions between:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

---

# 🧪 Test Coverage

Testing covers the major backend functionality:

* ✅ User registration
* ✅ User login
* ✅ JWT authentication
* ✅ JWT authorization
* ✅ Controller endpoints
* ✅ Service business logic
* ✅ Repository queries
* ✅ Blog CRUD
* ✅ Blog ownership
* ✅ Likes
* ✅ Bookmarks
* ✅ Comments
* ✅ User profile
* ✅ File upload logic
* ✅ Exception handling
* ✅ Validation
* ✅ HTTP status codes
* ✅ JSON responses
* ✅ Database operations
* ✅ Integration testing

---

# 📊 Testing Goal

The main goal of the testing strategy is to verify each layer independently and ensure that the complete Spring Boot application works correctly when all layers are integrated.

```text
             Unit Testing
                  +
          Controller Testing
                  +
          Service Testing
                  +
         Repository Testing
                  +
          Security Testing
                  +
        Integration Testing
                  ↓
       Reliable Spring Boot API
```

---

# 🗄️ Database

The application uses **Supabase PostgreSQL** as its primary database.

Main entities:

```text
User
 │
 ├──────────> Blog
 │              │
 │              ├────────> Comment
 │              │
 │              └────────> Bookmark
 │
 └──────────> RefreshToken
```

Database architecture:

```text
Spring Boot
     ↓
Spring Data JPA
     ↓
Hibernate
     ↓
PostgreSQL
     ↓
Supabase
```

---

# ☁️ Supabase Storage

Supabase Storage is used for blog and profile images.

```text
React.js
    ↓
Spring Boot
    ↓
Supabase Storage
    ↓
Image URL
    ↓
PostgreSQL
```

The backend handles the upload operation so that sensitive Supabase service credentials are not exposed to the React frontend.

---

# 🌐 Frontend–Backend Architecture

```text
                 ┌─────────────────────┐
                 │      React.js       │
                 │      Frontend       │
                 └──────────┬──────────┘
                            │
                         Axios
                            │
                            ▼
                 ┌─────────────────────┐
                 │     Spring Boot     │
                 │      REST API       │
                 └──────────┬──────────┘
                            │
               ┌────────────┴────────────┐
               │                         │
               ▼                         ▼
      ┌─────────────────┐       ┌─────────────────┐
      │    Supabase     │       │    Supabase     │
      │   PostgreSQL    │       │     Storage     │
      └─────────────────┘       └─────────────────┘
```

---

# 📌 Important Backend Components

## Controllers

```text
AuthController
BlogController
AdminController
CommentController
BookmarkController
FileController
UploadController
```

## DTOs

```text
AuthResponse
BlogRequest
BlogStats
CommentRequest
RefreshRequest
RequestLogin
UpdateProfileRequest
AdminDashboardResponse
```

## Main Entities

```text
User
Blog
Comment
Bookmark
RefreshToken
```

---

# 📮 API Testing with Postman

Recommended testing flow:

```text
1. Register User
       ↓
2. Login User
       ↓
3. Copy JWT Access Token
       ↓
4. Add Bearer Token
       ↓
5. Create Blog
       ↓
6. Get Blogs
       ↓
7. Get Blog by ID
       ↓
8. Update Blog
       ↓
9. Delete Blog
```

Additional API tests:

```text
Add Comment
     ↓
Like Blog
     ↓
Add Bookmark
     ↓
Get Bookmarks
     ↓
Update Profile
     ↓
Upload Image
```

---

# 🔐 Security

The application implements:

* JWT authentication
* Password hashing
* Spring Security
* Protected REST endpoints
* Ownership-based authorization
* Role-based authorization
* Refresh token mechanism
* CORS configuration
* Secure file upload handling
* Externalized sensitive configuration

Sensitive information such as:

```text
Database Password
JWT Secret
Supabase Service Key
```

should not be committed to GitHub.

---

# 🚀 Future Improvements

* Pagination
* Advanced blog search
* Blog filtering
* Blog category filtering
* Notification system
* Admin user management
* Email verification
* Forgot password
* Social login
* Redis caching
* Kafka event processing
* Docker deployment
* CI/CD pipeline
* Cloud deployment
* AI-powered blog recommendations
* AI content assistance

---

# 👩‍💻 Author

**Sharwari Ajay Rahangdale**

## Full-Stack Blog Application

### Technologies

```text
React.js
Spring Boot
Spring Security
JWT
PostgreSQL
Supabase
Hibernate
REST API
Swagger
JUnit 5
Mockito
MockMvc
Git & GitHub
```

---

# ⭐ Project Highlights

This project demonstrates practical experience in:

* Full-stack application development
* REST API development
* Spring Boot
* Spring Security
* JWT authentication
* React.js
* PostgreSQL
* Supabase integration
* Hibernate/JPA
* CRUD operations
* Ownership-based authorization
* Role-based authorization
* Refresh token authentication
* File upload and cloud storage
* Swagger/OpenAPI
* JUnit 5 testing
* Mockito unit testing
* MockMvc controller testing
* Repository testing with `@DataJpaTest`
* Integration testing with `@SpringBootTest`
* API testing with Postman
* Frontend-backend integration
* Git and GitHub
