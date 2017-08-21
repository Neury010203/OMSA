<!DOCTYPE html>
<html lang="en" ng-app="angularTable">
<#include "header.ftl">
<body>
<div id="wrapper">
<#include "nav.ftl">

    <div id="page-wrapper">

    <div class="container-fluid">

        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <h3 class="page-header">
                    Lista de Corredores
                </h3>
                <ol class="breadcrumb">
                    <li>
                        <i class="fa fa-dashboard"></i> <a href="/">Home</a>
                    </li>
                    <li class="active">
                        <i class="fa fa-table"></i> Tabla de Corredores
                    </li>
                </ol>
            </div>
        </div>
        <!-- /.row -->
        <!--
                <form action="">
                    <div class="row">
                        <div class=" col-lg-offset-8 col-lg-4">

                            <div class="form-group">
                                <label for="Ciudad">Ciudad</label>
                                <select class="form-control" name="ruta" id="ruta">
                                    <option selected disabled>Elija una opcion</option>
                                </select>
                            </div>

                        </div>
                    </div>

                </form>
-->
        <div class="row">
            <div class="col-lg-12">
                <h2> Rutas</h2>
                <div class="table-responsive" ng-controller="rutaTableControllers" ng-init="getData(pageno)" >
                    <table class="table table-bordered table-hover table-striped">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Nombre Corredor</th>
                                <th>Distancia Total (KM)</th>
                                <th>Ciudad</th>
                                <th>Es Direccion Subida</th>
                                <th>&nbsp</th>
                                <th>&nbsp</th>
                                <th>&nbsp</th>
                            </tr>
                        </thead>
                        <tbody>
                        <tr ng-show="rutas.length <= 0"><td colspan="8" style="text-align:center;">Leyendo Nuevas Rutas!!</td></tr>
                        <tr dir-paginate="r in rutas[0].content | itemsPerPage:itemsPerPage" total-items="${size}">
                            <td>{{$index+1}}</td>
                            <td>{{r.nombreCorredor}}</td>
                            <td>{{r.distanciaTotal}}</td>
                            <td>{{r.ciudad}}</td>
                            <td>{{r.esDireccionSubida}}</td>
                            <td><a href="/ruta/listar/paradas/{{r.id}}">Ver Ruta</a></td>
                            <td>
                                <a href="/ruta/editar/{{r.id}}">
                                    <p data-placement="top" data-toggle="tooltip" title="Edit"><button class="btn btn-primary btn-xs" data-title="Editar" data-toggle="modal" data-target="#edit"><span class="glyphicon glyphicon-pencil"></span></button></p>
                                </a>
                            </td>
                            <td>
                                <a href="/ruta/eliminar/{{r.id}}">
                                    <p data-placement="top" data-toggle="tooltip" title="Delete"><button class="btn btn-danger btn-xs" data-title="Eliminar" data-toggle="modal" data-target="#delete"><span class="glyphicon glyphicon-trash"></span></button></p>
                                </a>
                            </td>

                        </tr>
                        </tbody>
                    </table>
                    <center><dir-pagination-controls
                            max-size="10"
                            direction-links="true"
                            boundary-links="true"
                            on-page-change="getData(newPageNumber)" >
                    </dir-pagination-controls></center>







                        <#--<#if rutas??>-->
                            <#--<#list rutas as ruta>-->
                            <#--<tr>-->
                                <#--<th scope="row">${ruta?index+1}</th>-->
                                <#--<td><#if ruta.nombreCorredor??>${ruta.nombreCorredor}</#if></td>-->
                                <#--<td><#if ruta.distanciaTotal??>${ruta.distanciaTotal}</#if></td>-->
                                <#--<td><#if ruta.ciudad??>${ruta.ciudad}</#if></td>-->
                                <#--<td><#if ruta.esDireccionSubida>SI <#else> NO</#if></td>-->
                                <#--<td><a href="/ruta/listar/paradas/${ruta.id}">Ver Ruta</a></td>-->
                                <#--<td>-->
                                    <#--<a href="/ruta/editar/${ruta.id}">-->
                                        <#--<p data-placement="top" data-toggle="tooltip" title="Edit"><button class="btn btn-primary btn-xs" data-title="Editar" data-toggle="modal" data-target="#edit"><span class="glyphicon glyphicon-pencil"></span></button></p>-->
                                    <#--</a>-->
                                <#--</td>-->
                                <#--<td>-->
                                    <#--<a href="/ruta/eliminar/${ruta.id}">-->
                                        <#--<p data-placement="top" data-toggle="tooltip" title="Delete"><button class="btn btn-danger btn-xs" data-title="Eliminar" data-toggle="modal" data-target="#delete"><span class="glyphicon glyphicon-trash"></span></button></p>-->
                                    <#--</a>-->
                                <#--</td>-->

                            <#--</tr>-->

                            <#--</#list>-->
                        <#--</#if>-->

                        <#--</tbody>-->
                    <#--</table>-->
                </div>
            </div>
        </div>
        <!-- /.row -->

    </div>
    <!-- /.container-fluid -->

</div>
<!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->

<!-- jQuery -->
<script src="/js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/js/bootstrap.min.js"></script>

<script src="/js/dirPagination.js"></script>
<script src="/js/appTable.js"></script>
<script src="/js/tableControllers/rutaTableControllers.js"></script>

</body>

</html>