<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cities</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>

<body>

<div class="container-fluid">
    <div class="jumbotron mt-5">
        <h1>CITY MANAGEMENT</h1>
        <p>A CRUD OF CITIES</p>
    </div>

        <#if actualCitie??>
            <form action="/cities/edit" method="POST" class="needs-validation" novalidate>
            <input type="hidden" name="actualName" value="${(actualCitie.name)!}"/>
            <input type="hidden" name="actualState" value="${(actualCitie.state)!}"/>
        <#else>
            <form action="/cities" method="POST" class="needs-validation" novalidate>
        </#if>

        <div class="form-group">
            <label for="name">Cities:</label>
            <input required value="${(actualCitie.name)!}${informedName!}" name="name" type="text" class="form-control" placeholder="Citie" id="name">
            <div>
                ${name!}
            </div>
        </div>
        <div class="form-group">
            <label for="state">State:</label>
            <input maxlength="2" required value="${(actualCitie.state)!}${informedState!}" name="state" type="text" class="form-control" placeholder="State" id="state">
            <div>
                ${state!}
            </div>
        </div>

        <#if actualCitie??>
            <button type="submit" class="btn btn-warning">EDIT</button>
        <#else>
            <button type="submit" class="btn btn-primary">CREATE</button>
        </#if>
    </form>

    <table class="table table-striped table-hover mt-5">
        <thead class="thead-dark">
        <tr>
            <th>Name</th>
            <th>State</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <#list cities as citie>
            <tr>
                <td>${citie.name}</td>
                <td>${citie.state}</td>
                <td>
                    <div class="d-flex d-justify-content-center">
                        <a class="btn btn-warning mr-3" href="/cities/model?name=${citie.name}&state=${citie.state}">EDIT</a>
                        <a class="btn btn-danger" href="/cities/delete?name=${citie.name}&state=${citie.state}">DELETE</a>
                    </div>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
</body>

</html>