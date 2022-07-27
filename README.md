# product-review
BRIEF: a code challenge implementation consists of following subsystem(or modules):
- inventory
- sales 
- uaa: a simple jwt-authentication server
- aggregator: to aggregate productInfo data from inventory and sales

LOGIN-ACCOUNTS: mongock-dbchangelog created users all with same password "rsm825" :
 - "d.shiroodi" from samservice provider with PROVIDER_SALES_ADMIN role
 - "m.rezvani" from samservice provider with PROVIDER_SALES_PERSON role
 - "r.sajadi" from madiran provider with PROVIDER_SALES_ADMIN, PROVIDER_SALES_PERSON roles
 - "r.mirmardi" and "n.hematnasab" as customers

APIs: for getting more information load sh.badiei-CodeChallenge.postman_collection.json into Postman.
