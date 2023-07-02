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
