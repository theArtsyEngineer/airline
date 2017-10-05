<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Profile</title>
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.7/paper/bootstrap.min.css" rel="stylesheet" integrity="sha384-awusxf8AUojygHf2+joICySzB780jVvQaVCAt1clU3QsyAitLGul28Qxb2r1e5g+" crossorigin="anonymous">
    
    <link rel="stylesheet" href="css/main.css">
    
<body>
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <h3>User Profile</h3>
                <hr>
            </div>
        </div>
        
        <div class="row">
            <div class="col-xs-3">
                <h1>John Doe</h1>
                <p>Created 04/23/15</p>
                <p>act# 343435</p>
                <img src="" alt="">
            </div>
            
            <div class="col-xs-9">
                <div class="row">
                    <div class="col-xs-3">
                        <button class="btn btn-default btn-large btn-block" type="submit">Reservations</button>
                    </div>
                    <div class="col-xs-3">
                        <button class="btn btn-default btn-large btn-block" type="submit">Preferences</button>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <form action="">
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label for="inputFirst">First Name</label>
                                <input type="text" class="form-control" id="inputFirst" placeholder="First Name">
                            </div>
                            <div class="form-group">
                                <label for="inputAddress">Address</label>
                                <input type="text" class="form-control" id="inputAddress" placeholder="Address">
                            </div>
                            <div class="form-group">
                                <label for="inputPhone">Phone</label>
                                <input type="text" class="form-control" id="inputPhone" placeholder="Phone">
                            </div>
                            <div class="form-group">
                                <label for="inputCredit">Credit Card</label>
                                <input type="text" class="form-control" id="inputCredit" placeholder="Credit">
                            </div>
                        </div>
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label for="inputLast">Last Name</label>
                                <input type="text" class="form-control" id="inputLast" placeholder="Last Name">
                            </div>
                            
                            <div class="row">
                                <div class="col-xs-6">
                                    <div class="form-group">
                                        <label for="inputCity">City</label>
                                        <input type="text" class="form-control" id="inputCity" placeholder="City">
                                    </div>
                                </div>
                                <div class="col-xs-2">
                                    <div class="form-group">
                                        <label for="inputState">State</label>
                                        <input type="text" class="form-control" id="inputState" placeholder="AA">
                                    </div>
                                </div>
                                <div class="col-xs-4">
                                    <div class="form-group">
                                        <label for="inputZip">Zip Code</label>
                                        <input type="text" class="form-control" id="inputZip" placeholder="12345">
                                    </div>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label for="inputEmail">Email</label>
                                <input type="text" class="form-control" id="inputEmail" placeholder="example@gmail.com">
                            </div>
                            
                            <div class="row">
                                <div class="col-xs-6">
                                    <button class="btn btn-primary btn-large btn-block" type="submit">Update Info</button>
                                </div>
                                <div class="col-xs-6">
                                    <button class="btn btn-danger btn-large btn-block" type="submit">Delete Account</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            
        </div>
        
        <hr>
    </div>
        
    <!-- /container -->
</body>

</html>
