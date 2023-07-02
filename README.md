# Coin

A brief description of what this project does and who it's for

## Topics to be consumed

- payment-processed

- order-created

- post-created

## Topics to be produced

- package-added

- package-updated

- package-deleted
- coins-added
- coins-used

- coins-purchased

## API Reference

#### Get all transactions on a specific user

```http
  GET /transactions/{userId}
```

#### Get coins available in a specific user wallet

```http
  GET /coins/{userId}
```

## Package CRUDs

- Add Package:

```
http://localhost:8080/package/add

-- post body: // no id - gets auto generated from the API
{
    "name": "package 3",
    "description": "package 3 description",
    "coins": 200
}
```

- Update Package:

```
http://localhost:8080/package/edit

-- post body:
{
    "id": "64a08fc7950ec07c56fce835",
    "name": "package 3 last",
    "description": "package 3 description edit",
    "coins": 1000
}

```

- Delete Package:

```
http://localhost:8080/package/delete/${packageId}

-- example:
http://localhost:8080/package/delete/64a07f3da7d1035e2c46901b
```

- Get All Packages:

```
http://localhost:8080/package/all

```
