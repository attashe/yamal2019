<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Simple Sidebar - Start Bootstrap Template</title>

    <!-- Bootstrap core CSS -->
    <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../css/simple-sidebar.css" rel="stylesheet">
    <link href="../css/main.css" rel="stylesheet">

    <!-- Bootstrap core JavaScript -->
    <script src="../vendor/jquery/jquery.min.js"></script>
    <script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <link rel="stylesheet" type="text/css" href="../vendor/datatables/css/jquery.dataTables.min.css"/>

    <script type="text/javascript" src="../vendor/datatables/js/jquery.dataTables.min.js"></script>


</head>

<body>

<div class="d-flex" id="wrapper">

    <!-- Sidebar -->
    <div class="bg-light border-right" id="sidebar-wrapper">
        <div class="sidebar-heading">Start Bootstrap</div>
        <div class="list-group list-group-flush">
            <a href="#" class="list-group-item list-group-item-action bg-light" onclick="enableContent('animals')">
                Список животных</a>
            <a href="#" class="list-group-item list-group-item-action bg-light" onclick="enableContent('profile')">
                Мой профиль</a>
            <a href="#" class="list-group-item list-group-item-action bg-light" onclick="enableContent('schedule')">
                Мое расписание</a>
            <a href="#" class="list-group-item list-group-item-action bg-light"
               onclick="enableContent('notifications')">
                Уведомления</a>
            <a href="#" class="list-group-item list-group-item-action bg-light" onclick="enableContent('documents')">
                Документы</a>
            <a href="#" class="list-group-item list-group-item-action bg-light" onclick="enableContent('reports')">
                Отчеты</a>
        </div>
    </div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">

        <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
            <button class="btn btn-primary" id="menu-toggle">Toggle Menu</button>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            ${userName}
                        </a>
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" onclick="enableContent('profile')">Мой профиль</a>
                            <a class="dropdown-item" onclick="enableContent('schedule')">Мое расписание</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="/logout">Выход</a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>

        <#--      CONTENT-->
        <div id="animals" class="container-fluid">
            <table id="animalTable" class="display">
                <thead>
                <tr>
                    <th>id</th>
                    <th>name</th>
                    <th>gender</th>
                    <th>animalType</th>
                    <th>receivedDate</th>
                    <th>birthDate</th>
                    <th>animalStatus</th>
                    <th>lastDeWormingDate</th>
                    <th>sterilized</th>
                    <th>sterilizationDate</th>
                    <th>description</th>
                    <th>ownerDto</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>

        <div id="profile" class="container-fluid disabled-content">
            <p>fdsfdsfdfsdfsdf</p>
        </div>

        <div id="schedule" class="container-fluid disabled-content">
            <p>Schedule</p>
        </div>

        <div id="notifications" class="container-fluid disabled-content">
            NNNNNN
        </div>

        <div id="documents" class="container-fluid disabled-content">
            DOCS
        </div>

        <div id="reports" class="container-fluid disabled-content">
            RRRR
        </div>

        <#--      CONTENT-->
    </div>
    <!-- /#page-content-wrapper -->

</div>
<!-- /#wrapper -->


<!-- Menu Toggle Script -->
<script>
    $("#menu-toggle").click(function (e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });

    function enableContent(id) {
        $(".container-fluid").addClass("disabled-content");
        $("#" + id).removeClass("disabled-content");
    }

    $(document).ready(function () {
        $('#animalTable').dataTable({
            "ajax": {
                "url": '/animals',
                "dataType": 'json',
                "type": "GET",
                "beforeSend": function (xhr) {
                    xhr.setRequestHeader("Authorization", "Bearer ${jwt}");
                }
            },
            "data": [
                {

                }
            ]
            // "columns": [
            //     { "data": "id" },
            //     { "data": "name" },
            //     { "data": "gender" },
            //     { "data": "animalType" },
            //     { "data": "receivedDate" },
            //     { "data": "birthDate" },
            //     { "data": "animalStatus" },
            //     { "data": "lastDeWormingDate" },
            //     { "data": "sterilized" },
            //     { "data": "sterilizationDate" },
            //     { "data": "description" },
            //     { "data": "ownerDto" }
            // ]
        });
    });

</script>

</body>

</html>
