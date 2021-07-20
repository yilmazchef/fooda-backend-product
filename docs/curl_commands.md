# cURL Commands to test the Product API

**Recommendations**: Run these commands via Git Bash which secures testing on cross-platform.

## POST

to create product: 

```

curl --request POST \
  --url http://localhost:8080/api/v1/products/add/one \
  --header 'Authorization: Basic Zm9vZGE6ZjAwZEA=' \
  --header 'Content-Type: application/json' \
  --cookie JSESSIONID=A852F1FC1655381A4D4D7270FC773422 \
  --data '{
  "categories": [
    {
      "title": "pizza"
    },
		{
      "title": "student"
    }
  ],
  "defaultImage": {
    "mediaId": "253f264f-83b3-49a8-b64d-1e254aa3f675",
    "url": "https://www.fooda.be/253f264f-83b3-49a8-b64d-1e254aa3f675.jpg"
  },
  "description": "It'\''s the Italian classic.. No need for a description.. ",
  "etrackingId": "89773aaa-6847-4ff0-baa9-a263cc8c7a99",
  "ingredients": [
    {
      "price": 1.00,
      "title": "Buffalo Mozzarella"
    },
		{
      "price": 0.00,
      "title": "Basilicum"
    },
		{
      "price": 0.25,
      "title": "Tomatoes"
    }
  ],
  "isFeatured": true,
  "limitPerOrder": 100,
  "title": "Pizza Margheritta",
  "prices": [
    {
      "title": "Standard Price",
      "amount": 12.50
    },
		{
      "title": "Student Price",
      "amount": 9.90
    }
  ],
  "store": {
    "title": "Pizzify Brussels",
    "storeId": "a95b0846-c8e1-48ac-9bdb-9d38a8670f77"
  },
  "tags": [
    {
      "value": "pizza"
    },
		{
      "value": "margheritta"
    },
		{
      "value": "italian"
    },
		{
      "value": "student"
    }
  ],
  "taxes": [
    {
      "isDefault": true,
      "percentage": 0,
      "title": "Standard Tax"
    }
  ],
  "type": "SIMPLE"
}'

```

## PUT

## DELETE

## GET

## SEARCH