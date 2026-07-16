---
config:
  theme: redux
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
    n10@{ shape: rect}
    n9@{ shape: text}
    n11@{ shape: text}
    n12@{ shape: text}
    style n9 font-size:24px
    style n11 font-size:24px
    style n12 font-size:24px