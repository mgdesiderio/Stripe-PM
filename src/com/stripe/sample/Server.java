package com.stripe.sample;

import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;
import static spark.Spark.port;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.model.StripeObject;
import com.stripe.net.ApiResource;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;

public class Server {
  private static Gson gson = new Gson();

  static class CreatePayment {
    @SerializedName("items")
    Object[] items;

    public Object[] getItems() {
      return items;
    }
  }

  static class CreatePaymentResponse {
    private String clientSecret;

    public CreatePaymentResponse(String clientSecret) {
      this.clientSecret = clientSecret;
    }
  }

  static int calculateOrderAmount(Object[] items) {
    // Replace this constant with a calculation of the order's amount
    // Calculate the order total on the server to prevent
    // users from directly manipulating the amount on the client
    return items.length * 1200;
  }

  public static void main(String[] args) {
    port(4242);
    staticFiles.externalLocation(Paths.get("").toAbsolutePath().toString());

    // This is your real test secret API key.
    Stripe.apiKey = "sk_test_51HJ2hwLb59qJsyMou0Nffblgj5l83abCTQGlhPnn7ilU3RFBSOKdfpfGIaGnBI7HhjKHtXJiA5bFxpHYeHhijrxJ008kUoWYTx";

    post("/create-payment-intent", (request, response) -> {
      response.type("application/json");

      CreatePayment postBody = gson.fromJson(request.body(), CreatePayment.class);
      PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
      .setCurrency("usd")
      .setAmount(new Long(calculateOrderAmount(postBody.getItems())))
      .putMetadata("integration_check", "accept_a_payment")
      .build();
      // Create a PaymentIntent with the order amount and currency
      PaymentIntent intent = PaymentIntent.create(createParams);

      CreatePaymentResponse paymentResponse = new CreatePaymentResponse(intent.getClientSecret());
      return gson.toJson(paymentResponse);
    });

    post("/webhook", (request, response) -> {
      String payload = request.body();
      Event event = null;

      try {
        event = ApiResource.GSON.fromJson(payload, Event.class);
      } catch (Exception e) {
        // Invalid payload
        response.status(400);
        return "";
      }

      // Deserialize the nested object inside the event
      EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
      StripeObject stripeObject = null;
      if (dataObjectDeserializer.getObject().isPresent()) {
        stripeObject = dataObjectDeserializer.getObject().get();
      } else {
        // Deserialization failed, probably due to an API version mismatch.
        // Refer to the Javadoc documentation on `EventDataObjectDeserializer` for
        // instructions on how to handle this case, or return an error here.
      }

      // Handle the event
      if (event.getType().equals("payment_intent.succeeded")) {
        PaymentIntent paymentIntent = (PaymentIntent) stripeObject;
        String message = String.format("%s successful payment id %s for amount %s\r\n", new Timestamp(System.currentTimeMillis()), paymentIntent.getId(), paymentIntent.getAmount());
        Files.write(
                Paths.get("log.txt").toAbsolutePath(),
                message.getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
      }

      response.status(200);
      return "";
    });
  }
}