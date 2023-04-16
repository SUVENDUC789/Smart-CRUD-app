    $(document).ready(function () {

        function loadData() {
            $.ajax({
                url: "table",
                type: "POST",
                beforeSend: function () {
                    $("#student-data").html("<h1>Loading data ...</h1>");
                },
                success: function (data) {
                    $("#student-data").html(data);
                }
            });

        }

        loadData();

        $("#search_here").keyup(function(){
            let ser = $(this).val();
            $.ajax({
                url:"Search",
                type:"POST",
                data:{search:ser},
                success:function(data){
                    $("#student-data").html(data);
                }
            });

        });


        $(document).on("click", ".update-btn", function () {
            let id2 = $(this).data("id");
            let element = this;

            let first_name = prompt("Enter First Name : ");
            let last_name = prompt("Enter last Name : ");

            // console.log(first_name,last_name);

            $.ajax({
                url: "Update",
                type: "POST",
                data: { id: id2, fname: first_name, lname: last_name },
                beforeSend:function(){
                       $(".success-data").html(`<div class="alert alert-warning alert-dismissible fade show" role="alert">
                        <strong>Update SL = ${id2} </strong> ...
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>`);
                },
                success: function(){
                    loadData();
                }
            });

            alert(id + " " + first_name + " " + last_name);
        });

        $(document).on("click", ".delete-btn", function () {
            let id = $(this).data("id");
            let element = this;

            $.ajax({
                url: "delete",
                type: "POST",
                data: { sl: id },
                beforeSend: function () {
                    $(".success-data").html(`<div class="alert alert-warning alert-dismissible fade show" role="alert">
                        <strong>Delete SL = ${id} </strong> ...
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>`);
                },
                success: function () {
                    //$(element).closest("tr").fadeOut();
                    loadData();
                }
            });

            // alert(id);
        });

        $("#myform").submit(function (e) {
            e.preventDefault();
            let fn = $("#first-name").val();
            let ln = $("#last-name").val();
            let gen = $("#gender").val();



            $.ajax({
                url: "Insert",
                type: "POST",
                data: { first_name: fn, last_name: ln, gender: gen },
                beforeSend: function () {
                    $(".success-data").html(`<div class="alert alert-warning alert-dismissible fade show" role="alert">
                        <strong>${fn + " " + ln}</strong> Please wait ...
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>`);

                },
                success: function (data) {
                    $(".success-data").html(`<div class="alert alert-success alert-dismissible fade show" role="alert">
                    <strong>${fn + " " + ln}</strong>  your data inserted.
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>`);

                    loadData();

                    $("form").trigger("reset");
                }
            });


        });




    });
