# URL SHORTENER APP
- Takes the long user URL and return shortened url 
- Used Redis cache to speed up the frequently accessed url
- Long url is hashed and stored to database
- Short code generated usn base62 encoder
## Architecture Diagram
```mermaid
---
config:
  theme: mc
  layout: dagre
---
flowchart TB

%% ----------------------------
%% URL Creation
%% ----------------------------

Start([User submits Long URL])

Controller["Controller<br/>POST /api/create-short-url"]

Hash["Generate SHA-256 Hash"]

Check{"URL Hash<br/>Already Exists?"}

Existing["Return Existing<br/>Short Code"]

Create["Create UrlMappingEntity"]

Save["Save Entity<br/>(ID Auto Generated)"]

Encode["Generate Base62<br/>Short Code from ID"]

Update["Update Entity with<br/>Short Code"]

Response["Return Short URL"]

Start --> Controller
Controller --> Hash
Hash --> Check

Check -- Yes --> Existing
Existing --> Response

Check -- No --> Create
Create --> Save
Save --> Encode
Encode --> Update
Update --> Response

%% ----------------------------
%% URL Redirection
%% ----------------------------

Click([User clicks Short URL])

RedirectController["Controller<br/>GET /api/{shortCode}"]

Cache{"Short Code<br/>Found in Cache?"}

CacheHit["Retrieve Long URL<br/>from Cache"]

Database["Retrieve Long URL<br/>from Database"]

UpdateCache["Store Long URL<br/>in Cache"]

Redirect["HTTP 302 Redirect<br/>to Original URL"]

Click --> RedirectController
RedirectController --> Cache

Cache -- Yes --> CacheHit
CacheHit --> Redirect

Cache -- No --> Database
Database --> UpdateCache
UpdateCache --> Redirect
```