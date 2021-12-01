# CryptoFileSystemAuthentication
## CS355 Final Project

### Language used: 
Java

### Libraries used:
Only standard Java libraries were used:
java.net.*, java.util.Scanner, java.nio.charset.StandardCharsets, java.nio.file.Files, java.nio.file.Paths, java.security.MessageDigest, and java.io.\*

## Security analysis of encryption scheme
### Security goal:
Verify contents of a file is the same between two file systems

### How they are achieved:
- Use a server and client for the protocol communication.
- Both the server and client connect to each other and request a file name and create hashes in both SHA-1 and SHA-256 for the file content.
- The client is sending it's SHA-256 hash to the server and comparing the SHA-1 received from the server with its generated hash.
- The server is sending it's SHA-1 hash to the client as well and comparing the SHA-256 received from the client with its generated hash.
- Then after comparing the received hash with its generated one, the server and client will print if the files are a match and then close their sockets. 
