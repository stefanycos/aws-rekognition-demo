# AWS Rekognition
Demonstration of [AWS Rekognition](https://aws.amazon.com/pt/rekognition/?blog-cards.sort-by=item.additionalFields.createdDate&blog-cards.sort-order=desc) to detect text and get credit card numbers.

### Requirements
* Maven
* JDK 1.8

#### Running
* mvn spring-boot:run

#### Usage
* Detecting credit card number
<img src="https://github.com/stefanycos/aws-rekognition-demo/blob/master/src/main/resources/card-visa.png" width=250 />

__Request__
<br>*The above image was converted to base64*
```json
{
    "type": "CREDIT_CARD",
    "base64": "iVBORw0KGgoAAAANSUhEUgAAAloAAAF7CAMAAAA9ndXWAAAAAXNSR0IArs4c6QAAAwBQ..."
}
```

__Response__
```json
{
    "number": "4000 1234 5678 9010",
    "brand": "VISA"
}
```

* Detecting full image content

__Request__

```json
{
    "type": "DETECT_TEXT",
    "base64": "iVBORw0KGgoAAAANSUhEUgAAAloAAAF7CAMAAAA9ndXWAAAAAXNSR0IArs4c6QAAAwBQ..."
}
```

__Response__
```json
{
    "lines": [
        "Mission",
        "Lane",
        "4000 1234 5678 9010",
        "4000",
        "GOOD 09/22",
        "THRU",
        "CARDHOLDER NAME",
        "VISA"
    ]
}
```

