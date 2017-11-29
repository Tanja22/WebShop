var ispitApp = angular.module('ispitApp', ['ngRoute']);

ispitApp.config(['$routeProvider', function($routeProvider) {
    $routeProvider
        .when('/', {
          templateUrl : '/static/app/html/partials/piva.html'
        })
        .when('/piva', {
          templateUrl : '/static/app/html/partials/piva.html'
        })
        .when('/piva/edit/:id',{
          templateUrl: '/static/app/html/partials/edit-piva.html'
        })
        .otherwise({
          redirectTo: '/'
        });
}]);

ispitApp.controller('pivaCtrl', function($scope, $http, $location){

    var base_url_piva = "/api/piva";
    var base_url_pivare = "/api/pivare";

    $scope.pageNum = 0;
    $scope.totalPages = 0;
    $scope.izmena = false;

    $scope.pivare = [];
    $scope.piva = [];

    $scope.novoPivo = {};
    $scope.novoPivo.naziv = "";
    $scope.novoPivo.vrsta = "";
    $scope.novoPivo.alkohol = "";
    $scope.novoPivo.ibu = "";
    $scope.novoPivo.stanje = "";
    $scope.novoPivo.pivaraId = 0;
    $scope.novoPivo.pivaraNaziv = "";

    $scope.trazenoPivo = {};
    $scope.trazenoPivo.naziv = "";
    $scope.trazenoPivo.minIbu = "";
    $scope.trazenoPivo.maxIbu = "";
    $scope.trazenoPivo.pivaraNaziv = "";

    $scope.kupljenoPivo = {};
    $scope.kupljenoPivo.naziv = "";
    $scope.kupljenoPivo.vrsta = "";
    $scope.kupljenoPivo.alkohol = "";
    $scope.kupljenoPivo.ibu = "";
    $scope.kupljenoPivo.stanje = "";
    $scope.kupljenoPivo.pivaraId = "";
    $scope.kupljenoPivo.pivaraNaziv = "";



    var getPiva = function(){
      var config = {params: {}};

      config.params.pageNum = $scope.pageNum;

      if($scope.trazenoPivo.naziv!= ""){
        config.params.naziv = $scope.trazenoPivo.naziv;
      }

      if($scope.trazenoPivo.minIbu != ""){
        config.params.minIbu = $scope.trazenoPivo.minIbu;
      }

      if($scope.trazenoPivo.maxIbu != ""){
        config.params.maxIbu = $scope.trazenoPivo.maxIbu;
      }

      if($scope.trazenoPivo.pivaraNaziv != ""){
        config.params.pivaraNaziv = $scope.trazenoPivo.pivaraNaziv;
      }

      if(nestalo){
        config.params.stanje=0;
      }


      $http.get(base_url_piva, config)
           .then(function success(data){
               $scope.piva = data.data;
               $scope.totalPages = data.headers('totalPages');

           });
    };

    var getPivare = function(){
        $http.get(base_url_pivare)
             .then(function success(data){
                 $scope.pivare = data.data;
         });
    }

    getPivare();
    getPiva();

    $scope.nazad = function(){
           $scope.pageNum = $scope.pageNum - 1;
           getPiva();
   };

   $scope.napred = function(){
          $scope.pageNum = $scope.pageNum + 1;
          getPiva();

  };

  $scope.dodaj = function(){
        $http.post(base_url_piva, $scope.novoPivo)
            .then(function success(data){
                console.log(data.data);
                alert("Uspesno dodato u bazu.");
                getPiva();

                $scope.novoPivo = {};
                $scope.novoPivo.naziv = "";
                $scope.novoPivo.vrsta = "";
                $scope.novoPivo.alkohol = "";
                $scope.novoPivo.ibu = "";
                $scope.novoPivo.stanje = "";
                $scope.novoPivo.pivaraId = 0;
                $scope.novoPivo.pivaraNaziv = "";

          });
    };

    $scope.trazi = function () {
        $scope.pageNum = 0;
        getPiva();
    }

    $scope.izmeni = function(id){
       $location.path('/piva/edit/' + id);
   }

   $scope.obrisi = function(id){
      $http.delete(base_url_piva + "/" + id).then(
          function success(data){
              getPiva();
          },
          function error(data){
              alert("Neuspesno brisanje!");
              console.log(data);
          }
      );
  }

  $scope.izmeniNaIstoj = function(id){
    $scope.izmena = true;
    $http.get(base_url_piva + "/" + id)
        .then(function success(data){
           $scope.novoPivo = data.data;
        });
      }

    $scope.update = function(){
      $http.put(base_url_piva + "/" + $scope.novoPivo.id, $scope.novoPivo)
          .then(function success(data){
              alert("Uspešno izmenjen objekat!");

              $scope.izmena = false;
              getPiva();

              $scope.novoPivo = {};
              $scope.novoPivo.naziv = "";
              $scope.novoPivo.vrsta = "";
              $scope.novoPivo.alkohol = "";
              $scope.novoPivo.ibu = "";
              $scope.novoPivo.stanje = "";
              $scope.novoPivo.pivaraId = "";
              $scope.novoPivo.pivaraNaziv = "";

          });
    }

    $scope.kupi = function(id){
      $http.get(base_url_piva + "/" + id)
          .then(function success(data){
             $scope.kupljenoPivo = data.data;
          });

      if( $scope.kupljenoPivo.stanje == 0 ){
        alert("Trazenog piva nema na stanju!");
      }
      else{
        $scope.kupljenoPivo.stanje = $scope.kupljenoPivo.stanje-1;
        $http.put(base_url_piva + "/" + $scope.kupljenoPivo.id, $scope.kupljenoPivo)
            .then(function success(data){
                alert("Uspešno kupljen objekat!");

                });

                $scope.kupljenoPivo = {};
                $scope.kupljenoPivo.naziv = "";
                $scope.kupljenoPivo.vrsta = "";
                $scope.kupljenoPivo.alkohol = "";
                $scope.kupljenoPivo.ibu = "";
                $scope.kupljenoPivo.stanje = "";
                $scope.kupljenoPivo.pivaraId = "";
                $scope.kupljenoPivo.pivaraNaziv = "";


      }
      getPiva();
    }

});

ispitApp.controller("editPivaCtrl", function($scope, $http, $routeParams, $location){

    var base_url_piva = "/api/piva";

    $scope.staroPivo = {};
    $scope.staroPivo.naziv = "";
    $scope.staroPivo.vrsta = "";
    $scope.staroPivo.alkohol = "";
    $scope.staroPivo.ibu = "";
    $scope.staroPivo.stanje = "";
    $scope.staroPivo.pivaraId = "";
    $scope.staroPivo.pivaraNaziv = "";

    var getStaroPivo = function(){

        $http.get(base_url_piva + "/" + $routeParams.id)
            .then(function success(data){
               $scope.staroPivo = data.data;
            });

    }
    getStaroPivo();

    $scope.izmeni = function(){
        $http.put(base_url_piva + "/" + $scope.staroPivo.id, $scope.staroPivo)
            .then(function success(data){
                alert("Uspešno izmenjen objekat!");
                $location.path("/");
            });
    }
});
