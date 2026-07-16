# URL SHORTENER APP
Takes the long user URL and return shortened url 
## Architecture Diagram
```mermaid
---
config:
  theme: mc
  layout: fixed
---
flowchart TB
    n1["Create a new short URL"] --> n2["Controller(/api/create-short-url)"]
    n2 --> n3["Create new UrlMapping Enity object"]
    n3 --> n4["Save the object in database"]
    n5["Make short code from Entity Id"] --> n6["Save the short code in database abd send to the user"]
    n4 --> n8["Entity Exist"]
    n8 --> n5 & n10["Return the Short code"]
    n9["No"]
    n11["Yes"]
    n12["URL SHORTENER APP"]

    n1@{ shape: rect}
    n8@{ shape: decision}
    n10@{ shape: rect}---
config:
  theme: mc
  layout: fixed
---
flowchart TB
    Start(["User submits Long URL"]) --> Controller["Controller<br>POST /api/create-short-url"]
    Controller --> Hash["Generate SHA-256 Hash"]
    Hash --> Check{"URL Hash<br>Already Exists?"}
    Check -- Yes --> Existing["Return Existing<br>Short Code"]
    Existing --> Response["Return Short URL"]
    Check -- No --> Create["Create UrlMappingEntity"]
    Create --> Save["Save Entity<br>(ID Auto Generated)"]
    Save --> Encode["Generate Base62<br>Short Code from ID"]
    Encode --> Update["Update Entity with<br>Short Code"]
    Update --> Response
    n9@{ shape: text}
    n11@{ shape: text}
    n12@{ shape: text}
    style n9 font-size:24px
    style n11 font-size:24px
    style n12 font-size:24px
```