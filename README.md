# CryptoFileSystemAuthentication
## CS355 Final Project

### Language used: 
Java


### Libraries used:
Only standard Java libraries were used:
java.net.*, java.util.Scanner, java.nio.charset.StandardCharsets, java.nio.file.Files, java.nio.file.Paths, java.security.MessageDigest, and java.io.\*

## Security analysis of encryption scheme
### Security goal:
Verify contents of a file is the same between two filesystems

### How they are achieved:
- Use a server and client for the protocol communication.
- Both the server and client connect to each other and request a file name and create hashes in both SHA-1 and SHA-256 for the file content.
- The client is sending its SHA-256 hash to the server and comparing the SHA-1 received from the server with its generated hash.
- The server is sending its SHA-1 hash to the client as well and comparing the SHA-256 received from the client with its generated hash.
- Then after comparing the received hash with its generated one, the server and client will print if the files are a match and then close their sockets. 

### Why it's secure:
We use hashes because they are irreversable so don't reveal anything about what they hashed. 
Due to the system using two different types of hashes, both sides validate the equality of the files using a different hash so nothing would be revealed and prevents one party from receiving a hash and sending back the same hash.  We use two algorithms, the server and client, in order to have each algorithm check a hash and confirm that their file hashes match without having the other sides file content revealed.
