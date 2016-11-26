cd \
cd c:
mkdir \data\rs1
mkdir \data\rs2
mkdir \data\rs3
start /b mongod --replSet rs1 --dbpath \data\rs1 --logpath \data\rs1\log --port 27017 --smallfiles --oplogSize 64
start /b mongod --replSet rs1 --dbpath \data\rs2 --logpath \data\rs2\log --port 27018 --smallfiles --oplogSize 64
start /b mongod --replSet rs1 --dbpath \data\rs3 --logpath \data\rs3\log --port 27019 --smallfiles --oplogSize 64
::from here insert one by one
mongo --port 27017
rs.initiate({_id: "rs1", members: [{_id: 0, host: "localhost:27017"}, {_id: 1, host: "localhost:27018"}, {_id: 2, host: "localhost:27019"}]})
::rs.status()
exit