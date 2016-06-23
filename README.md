# Java Signature Pad

Java Signature Pad: a Java application that records a drawn signature in JSON for later regeneration.

This JSON signature is fully compatible with SignaturePad, a jQuery plugin for assisting in the creation of an HTML5 canvas based signature pad (https://github.com/thomasjbradley/signature-pad)

## Quick Start

1. This is a Java Netbeans project. It launches a small GUI where you can "sign" with the mouse, and then it generates the geometric JSON representation of that signature.  
2. You'll need to change the paths in SignatureCapture.java (line 98) and SignatureRegenerate.java (line 78) to your local filesystem when testing.  That is where the JSON signature will be saved.
3. The project uses gson-2.1.jar library which you can download
4. You can run SignatureRegnerate.java to regenerate the signature if you want to test that it's working properly
