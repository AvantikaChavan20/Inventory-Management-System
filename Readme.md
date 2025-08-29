flowchart TB
%% Clients
C[Client / Frontend]


%% Edge layer
AG[API Gateway
(8080)
JWT validation]


%% Discovery
EU[Eureka Server
(8761)]


%% Services
subgraph S[Microservices]
US[User Service
Auth/JWT]
PS[Product Service]
IS[Inventory Service]
CS[Cart Service]
PAY[Payment Service]
end


%% Databases
subgraph DBs[Per-service Databases]
UDB[(User DB)]
PDB[(Product DB)]
IDB[(Inventory DB)]
CDB[(Cart DB)]
PAYDB[(Payment DB)]
end


%% Requests via API Gateway
C --> AG
AG -. register/discover .-> EU
AG --> US
AG --> PS
AG --> IS
AG --> CS
AG --> PAY


%% Inter-service calls
CS -. get product info .-> PS
IS -. validate product .-> PS
PAY -. read total & userId .-> CS


%% Security notes
C -. login .-> AG
AG --> US
US -. issue JWT .-> C


%% DB ownership
US --> UDB
PS --> PDB
IS --> IDB
CS --> CDB
PAY --> PAYDB
