'use strict'
var module = angular.module('demo.controllers', []);
module.controller("userController", ["$scope", "UserService","$window",
    function($scope, UserService) {
        $scope.userDto = {
            userId: null,
            userName: null,
            skillDtos: []
        };

        UserService.isUserLoggedInService();


    }]);