# Accept a Card Payment

Build a simple checkout form to collect card details. Included are some basic build and run scripts you can use to start up the application.

## Running the sample

1. Build the server

```
npm install homebrew

brew tap AdoptOpenJDK/openjdk 

brew cask install adoptopenjdk8

Brew install maven

echo 'export PATH="/usr/local/opt/openjdk/bin:$PATH"' >> ~/.zshrc

mvn package
```

2. Run the server

```
java -cp target/stripe-card-payment-1.0.0-SNAPSHOT-jar-with-dependencies.jar com.stripe.sample.Server
```

3. Build the client app

```
npm install
```

4. Run the client app

```
npm start
```

5. Go to [http://localhost:3000/checkout](http://localhost:3000/checkout)

<Testing info>
