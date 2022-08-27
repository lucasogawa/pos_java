<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Clients</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>

<body>
    <div class="container-fluid">
        <div class="jumbotron mt-5">
            <h1>CLIENTS MANAGEMENT</h1>
            <p>A CRUD OF CLIENTS</p>
        </div>

        <form action="/clients" method="POST">
            <div class="form-group">
                <label for="name">Code:</label>
                <input value="${(client.code())!}" name="code" type="number" class="form-control" placeholder="Code"
                       id="name">
            </div>
            <div class="form-group">
                <label for="name">Name:</label>
                <input value="${(client.name())!}" name="name" type="text" class="form-control" placeholder="Name"
                       id="name">
            </div>
            <div class="form-group">
                <label for="city">City:</label>
                </br>
                <select id="city" name="city">
                    <#list cities as city>
                        <option <#if client?? && city.nome() == client.city()> selected="selected"</#if>>${city.nome()}</option>
                    </#list>
                </select>
            </div>

            <button type="submit" class="btn btn-primary">CREATE</button>
        </form>

        <table class="table table-striped table-hover mt-5">
            <thead class="thead-dark">
            <tr>
                <th>Code</th>
                <th>Name</th>
                <th>City</th>
            </tr>
            </thead>
            <tbody>
                <#list clients as client>
                    <tr>
                        <td>${client.code()}</td>
                        <td>${client.name()}</td>
                        <td>${client.city()}</td>
                    </tr>
                </#list>
            </tbody>
        </table>
    </div>
</body>

</html>