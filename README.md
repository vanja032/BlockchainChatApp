# Blockchain Chat App
### Fully developed system for the chat app which is based on the blockchain. The project includes following submodules:
- Blockchain node with a smart contract that is written in **C++** language for storing messages from the chat app
- Middleware web server written in **Python** programming language that listens on several endpoints and pushes transactions to the blockchain with encrypted data. <br>
***AES*** encryption is used for encrypting the data that uses blockchain chatapp account ***private key*** for the encryption. <br>
 Database model that is used for the app is ***NoSQL*** data model<br>
 The data looks like the following json document:
```
{
  "user_id": 2,
  "username": "user2",
  "password": "253a301a1c4a7b3c3667bb0c5952329f",
  "email": "36385f32038b0a492f0591016a2a0fcb",
  "f_name": "daf3458c8618ffca56ccfbdf28bcaa46",
  "l_name": "daf3458c8618ffca56ccfbdf28bcaa46",
  "picture": "bcd9738cefed041c04c774508a40879e17f1448886841019f891b22c568dff41ddc88c5ecb024abc997ee088d56ceb61a7e5c07c2ba9a7251f4b2a11f40af7415ff962b9378b2abaacb8635c80d15c0d4512a6587fb9ea2da6e7696ddaf0cee02f758b51eef40d54d23942e5b156d7c4dfc8d2e4486b60bf0e8869b55ccbadcd016e181dd095d3e522341c31b5551450949b67dedc5533334a99cb874e9c0fd9",
  "connections": [{
      "connection": 1,
      "username": "user1",
      "f_name": "283a0a8eeef0b49b18115e107dd60b7c",
      "l_name": "283a0a8eeef0b49b18115e107dd60b7c",
      "picture": "bcd9738cefed041c04c774508a40879e17f1448886841019f891b22c568dff41ddc88c5ecb024abc997ee088d56ceb61a7e5c07c2ba9a7251f4b2a11f40af7415ff962b9378b2abaacb8635c80d15c0d4512a6587fb9ea2da6e7696ddaf0cee02f758b51eef40d54d23942e5b156d7c4dfc8d2e4486b60bf0e8869b55ccbadcd016e181dd095d3e522341c31b5551450949b67dedc5533334a99cb874e9c0fd9",
      "time": 1674075416
    }
  ],
  "messages": [{
      "subject": 0,
      "status": "received",
      "username": "user",
      "f_name": "564150ca3bd5c5439110b20435cc0366",
      "l_name": "564150ca3bd5c5439110b20435cc0366",
      "picture": "bcd9738cefed041c04c774508a40879e17f1448886841019f891b22c568dff41ddc88c5ecb024abc997ee088d56ceb61a7e5c07c2ba9a7251f4b2a11f40af7415ff962b9378b2abaacb8635c80d15c0d4512a6587fb9ea2da6e7696ddaf0cee02f758b51eef40d54d23942e5b156d7c4dfc8d2e4486b60bf0e8869b55ccbadcd016e181dd095d3e522341c31b5551450949b67dedc5533334a99cb874e9c0fd9",
      "message": "11e432396f3978c80104aa9c2b03d563",
      "time": 1674138460
    },{
      "subject": 0,
      "status": "received",
      "username": "user",
      "f_name": "564150ca3bd5c5439110b20435cc0366",
      "l_name": "564150ca3bd5c5439110b20435cc0366",
      "picture": "bcd9738cefed041c04c774508a40879e17f1448886841019f891b22c568dff41ddc88c5ecb024abc997ee088d56ceb61a7e5c07c2ba9a7251f4b2a11f40af7415ff962b9378b2abaacb8635c80d15c0d4512a6587fb9ea2da6e7696ddaf0cee02f758b51eef40d54d23942e5b156d7c4dfc8d2e4486b60bf0e8869b55ccbadcd016e181dd095d3e522341c31b5551450949b67dedc5533334a99cb874e9c0fd9",
      "message": "13df42b8ae25e450d2d79d34df3075eb4c0dee76731c356811aceb40bdcf7113",
      "time": 1674166593
    }
  ]
}
```
- Mobile chat app that is written in **Kotlin** and **Java** programming languages in Android Studio engine.
