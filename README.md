# pdfjs4JFX
A full working example on using pdfjs with a webview in Java/JavaFX 11. This is based on the tutorial of Sam' blog here: https://blog.samirhadzic.com/2017/02/09/show-pdf-in-your-application/?replytocom=10135#respond

Tested with Java 8 and Java 11 the approach of using pdfjs does not work with the latest build but ONLY with the legacy build. This might not be true for later Java versions, e.g. Java 17 because there the webview was updated. Therefore, this example uses the pdfjs legacy build version 2.8.335. To update this please feel free to make a PR.

If you use a lower Java version just remove the stuff you do not need, e.g. module-info.java.
