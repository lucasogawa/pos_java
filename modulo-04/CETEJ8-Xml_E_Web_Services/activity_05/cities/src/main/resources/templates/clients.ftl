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

    <#if client??>
    <form action="/clients/update" method="POST">
        <input type="hidden" name="actualCode" value="${(client.code())!}"/>
        <input type="hidden" name="actualName" value="${(client.name())!}"/>
        <input type="hidden" name="actualCity" value="${(client.city())!}"/>
        <#else>
        <form action="/clients" method="POST">
            </#if>

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

            <#if client??>
                <button type="submit" class="btn btn-warning">EDIT</button>
            <#else>
                <button type="submit" class="btn btn-primary">CREATE</button>
            </#if>
        </form>

        <table class="table table-striped table-hover mt-5">
            <thead class="thead-dark">
            <tr>
                <th>Code</th>
                <th>Name</th>
                <th>City</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <#list clients as client>
                <tr>
                    <td>${client.code()}</td>
                    <td>${client.name()}</td>
                    <td>${client.city()}</td>
                    <td>
                        <div class="d-flex d-justify-content-center">
                            <a class="btn btn-warning mr-3"
                               href="/clients/model?code=${client.code()}">EDIT</a>
                            <a class="btn btn-danger"
                               href="/clients/delete?code=${client.code()}">DELETE</a>
                        </div>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </form>
</div>
</body>

</html>