<!DOCTYPE html>
<html lang="en"  xmlns:th="http://www.thymeleaf.org">
<#include "header.ftl">

<body>
<div id="wrapper">
<#include "nav.ftl">

    <div id="page-wrapper">

        <div class="container-fluid">

            <!-- Page Heading -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Editar una parada
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="/">Home</a>
                        </li>

                        <li class="active">
                            <i class="fa fa-edit"></i> Editar Parada
                        </li>
                    </ol>
                </div>
            </div>
            <form role="form" action="#" th:action="@{/parada/editar}" th:object="${parada}" method="POST">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label for="nombre">Nombre</label>
                            <input type="text" class="form-control" min="2" max="100" name="nombre" id="nombre" value="${parada.nombre}" required>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label for="ruta">Ruta</label>
                            <input type="text" readonly <#if ruta??>value="${ruta.nombreCorredor} | <#if ruta.esDireccionSubida> Subida <#else> Bajada</#if></#if>"/>
                            <input type="hidden" id="ruta" name="ruta" value="${ruta.id}">
                        </div>
                    </div>

                </div>
                <div class="row">
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label for="latitude">Latitud</label>
                            <input type="number" id="latitude" step="0.000001" name="latitude" class="form-control" value="${parada.coordenada.latitude}">
                        </div>

                    </div>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label for="longitud">Longitud</label>
                            <input type="number" class="form-control" step="0.000001" name="longitud" id="longitud" value="${parada.coordenada.longitud}">
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label for="paradaAnterior">Parada anterior</label>
                            <select class="form-control" name="paradaAnterior" id="paradaAnterior">
                                <option selected disabled>Elija una parada anterior</option>
                            <#if paradas>
                                <#list paradas as paradas></#list>
                                <option value="${parada.id}">${parada.nombre}</option>
                            </#if>
                            </select>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label for="paradaSiguiente">Parada Siguiente</label>
                            <select class="form-control" name="paradaSiguiente" id="paradaSiguiente">
                                <option selected disabled>Elija una parada siguiente</option>
                            <#if paradas>
                                <#list paradas as paradas></#list>
                                <option value="${parada.id}">${parada.nombre}</option>
                            </#if>
                            </select>
                        </div>
                    </div>
                </div>


        </div>
        <div class="row">
            <hr>
            <div class="col-lg-offset-6 col-lg-6">
                <div class="col-lg-6">
                    <div class="form-group">
                        <button type="reset" class="btn btn-success form-control">Limpiar</button>
                    </div>

                </div>
                <div class="col-lg-6">
                    <div class="form-group">
                        <button type="submit" class="btn btn-success form-control">Guardar</button>
                    </div>

                </div>

            </div>
        </div>

        </form>
    </div>
    <!-- /.container-fluid -->

</div>
<!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->

<!-- jQuery -->
<script src="/js/jquery.js">
</script>

<!-- Bootstrap Core JavaScript -->
<script src="/js/bootstrap.min.js"></script>

</body>

</html>