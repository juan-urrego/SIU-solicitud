package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.Consulta;
import com.solicitud.solicitud.entity.Estudio;
import com.solicitud.solicitud.entity.Solicitud;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private SolicitudService solicitudService;

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private EstudioService estudioService;

    public String exportReport(int id) throws FileNotFoundException, JRException {
        Solicitud solicitud = solicitudService.getSolicitudById(id);
        //Load file and compile it
        File file = ResourceUtils.getFile("classpath:reports/solicitud_ppal.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JasperReport detalles = JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:reports/detalles.jrxml").getAbsolutePath());
        JasperReport precotizaciones = JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:reports/precotizaciones.jrxml").getAbsolutePath());
        JasperReport argumentos = JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:reports/argumentos.jrxml").getAbsolutePath());
        JRBeanArrayDataSource dataSource = new JRBeanArrayDataSource(new Solicitud[]{solicitud});
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Juan");
        parameters.put("logo", ResourceUtils.getFile("classpath:reports/logo.png").getAbsolutePath());
        parameters.put("detallesParameter", detalles);
        parameters.put("precotizacionesParameter", precotizaciones);
        parameters.put("argumentosParameter", argumentos);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\juan-\\Desktop"+"\\solicitud.pdf");
        return "report generate";
    }

    public String exportConsulta(int id) throws FileNotFoundException, JRException {
        Consulta consulta = consultaService.getConsultaById(id);
        //Load file and compile it
        File file = ResourceUtils.getFile("classpath:reports/consultas.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JasperReport detalles = JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:reports/detalles.jrxml").getAbsolutePath());
        JasperReport precotizaciones = JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:reports/precotizaciones.jrxml").getAbsolutePath());
        JasperReport argumentos = JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:reports/argumentos.jrxml").getAbsolutePath());
        JRBeanArrayDataSource dataSource = new JRBeanArrayDataSource(new Consulta[]{consulta});
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Juan");
        parameters.put("logo", ResourceUtils.getFile("classpath:reports/logo.png").getAbsolutePath());
        parameters.put("detallesParameter", detalles);
        parameters.put("precotizacionesParameter", precotizaciones);
        parameters.put("argumentosParameter", argumentos);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\juan-\\Desktop"+"\\consulta.pdf");
        return "report generate";
    }

    public String exportEstudio(int id) throws FileNotFoundException, JRException {
        Estudio estudio = estudioService.getEstudioById(id);
        //Load file and compile it
        File file = ResourceUtils.getFile("classpath:reports/estudios.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JasperReport detalles = JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:reports/detalles.jrxml").getAbsolutePath());
        JRBeanArrayDataSource dataSource = new JRBeanArrayDataSource(new Estudio[]{estudio});
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Juan");
        parameters.put("logo", ResourceUtils.getFile("classpath:reports/logo.png").getAbsolutePath());
        parameters.put("detallesParameter", detalles);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\juan-\\Desktop"+"\\estudios.pdf");
        return "report generate";
    }
}
