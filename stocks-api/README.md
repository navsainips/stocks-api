# Stocks API

## Usage

    - mvn clean package
    - docker-compose up -d

    - goto http://localhost:8080/swagger-ui/index.html
    
    OR
    
    - curl -F "file=@dow_jones_index.data" -H "X-Client_Id: abc123" http://127.0.0.1:8080/api/stock-data/bulk-insert 
    - curl -H "X-Client_Id: abc123" http://127.0.0.1:8080/api/stock-data/AA
    - curl -H "Content-Type: application/json" -H "X-Client_Id: abc123" -X POST -d @dow_jones_missing.json http://127.0.0.1:8080/api/stock-data/
