'use strict'
angular.module('demo.services', []).factory('UserService', ["$http", "CONSTANTS","$window","$location",
    function($http, CONSTANTS,$window,$location) {
    var service = {};

    service.isUserLoggedInService =function () {
        var url=CONSTANTS.isUserLoggedIn;
       $http.get(url).success(function (response) {

           console.log(response);
           // $ngRoute.location.href="/loginpage";
           $window.location.href='#/loginpage';
       });
    }
    service.getUserById = function(userId) {
        var url = CONSTANTS.getUserByIdUrl+userId;
        return $http.get(url);
    }
    service.getAllUsers = function() {
        return $http.get(CONSTANTS.getAllUsers);
    }
    service.saveUser = function(userDto) {
        return $http.post(CONSTANTS.saveUser, userDto);
    }
    return service;
}]);