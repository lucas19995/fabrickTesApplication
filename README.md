# Fabrick Test Application


It's a client layer based on SpringBoot, used to call three API exposed by Fabrick:

 - GET    /api/gbs/banking/v4.0/accounts/{accountId} -> Retrieve Cash Account
 - GET   /api/gbs/banking/v4.0/accounts/{accountId}/transactions?{fromAccountingDate}&{toAccountingDate} -> Retrieve Account Transactions
 - POST  /api/gbs/banking/v4.0/accounts/{accountId}/payments/money-transfers -> Create a new payment



# The client APIs are:

BASEPATH : napolil/bank/
 - GET    account-cash -> Retrieve Cash Account
 - GET    transactions?{fromAccountingDate}&{toAccountingDate} -> Retrieve Account Transactions
 - POST   money-transfers -> Create a new payment

# Unit Test

 It's possible to test the three api executing the junit test class.
 
# Spring Documentation
 
 At http://localhost:8080/swagger-ui/index.html is possible to see the documentation about the APIs.
