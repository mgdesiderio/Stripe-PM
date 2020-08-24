# Accept a Card Payment
Here's my app for accepting a card payment for a business called Paul's Hot Salsa. It is a simple checkout form to collect card details. Before you begin: 

- You'll need to ensure you've downloaded the project and are able to run Java on your computer. 
- Make sure you have access to your terminal command line tool. 

## Let's get started
1. Once you've downloaded the project, set up your environment with the following commands:
    ```
    npm install homebrew

    brew tap AdoptOpenJDK/openjdk 

    brew cask install adoptopenjdk8

    brew install maven

    echo 'export PATH="/usr/local/opt/openjdk/bin:$PATH"' >> ~/.zshrc
    ```
2. Build the server by entering the follow simple command lines in your terminal. 
    ```
    mvn package
    ```
3. Run the server
    ```
    java -cp target/stripe-card-payment-1.0.0-SNAPSHOT-jar-with-dependencies.jar com.stripe.sample.Server
    ```
4. Build the client app (in a new terminal tab)
   ```
   npm install
   ```
6. Run the client app
    ```
    npm start
    ```
7. Enable log messages (Optional) (in a new terminal tab)
    ```
    stripe login --api-key sk_test_51HJ2hwLb59qJsyMou0Nffblgj5l83abCTQGlhPnn7ilU3RFBSOKdfpfGIaGnBI7HhjKHtXJiA5bFxpHYeHhijrxJ008kUoWYTx

    stripe listen --forward-to http://localhost:4242/webhook
    ```
7. To run the website locally, go to [http://localhost:3000/checkout](http://localhost:3000/checkout)

## Let's test some use cases
1. To accept a payment that does not require authentication, enter 4242424242424242 as the card number, any date in the future as the expiry, any CVV, and any zip. Press Pay. 

Expected Behavior: You should see a message stating the payment is successful. You should also see the successful payment logged to the log.txt file. 

2. To test a payment that requires authentication, enter 4000002500003155 as the card number, any date in the future as the expiry, any CVV, and any zip. Press Pay.
- Test the "Complete Authorization" path to see the experience when a user successfully completes authorization. 

Expected Behavior: You should see a message stating the payment is successful. You should also see the successful payment logged to the log.txt file.
- Test the "Fail Authorization" path to see the experience when a user fails to complete authorization.

Expected Behavior: You should see a message stating the payment failed authorization. You should not see this payment logged to the log.txt file. 

3. To test a card decline for insufficient funds, enter 4000000000009995 as the card number, any date in the future as the expiry, any CVV, and any zip. Press Pay.

Expected Behavior: You should see a message stating the payment failed due to insufficient funds. You should not see this payment logged to the log.txt file. 
