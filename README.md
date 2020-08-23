# Accept a Card Payment
Here's my app for accepting a card payment for a business called Paul's Hot Salsa. It is a 
Build a simple checkout form to collect card details. How it works: 
[TODO]

## Running the sample
1. Environment setup
    ```
    npm install homebrew

    brew tap AdoptOpenJDK/openjdk 

    brew cask install adoptopenjdk8

    Brew install maven

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
7. Go to [http://localhost:3000/checkout](http://localhost:3000/checkout)

## Test cases
[TODO]