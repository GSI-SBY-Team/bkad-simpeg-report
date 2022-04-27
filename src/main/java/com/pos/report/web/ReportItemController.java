package com.pos.report.web;

import com.pos.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("reports/items/")
public class ReportItemController {

    @Autowired
    ServletContext context;

    @Autowired
    ReportService reportService;

    @RequestMapping(value = "report1*", method = RequestMethod.GET)
    public void report1(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        String format = uri.substring(uri.lastIndexOf(".") + 1);
        String realPath = context.getRealPath("/WEB-INF/templates/jrxml/") + System.getProperty("file.separator");
        realPath = realPath.replace("\\", "\\\\");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("realPath", realPath);

        reportService.generateReport("report1", format, parameters, response, "report-");

    }

    @RequestMapping(value = "barcode*", method = RequestMethod.GET)
    public void Barcode(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        String format = uri.substring(uri.lastIndexOf(".") + 1);
        String ids = request.getParameter("ids");
        String[] items = ids.split(";");
        ArrayList<String> itemParams = new ArrayList<String>();
        for (String i : items) {
            itemParams.add("'" + i + "'");
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ids", itemParams);

        reportService.generateReport("ItemBarcode", format, parameters, response, "item-barcode");

    }

    @RequestMapping(value = "by-grup*", method = RequestMethod.GET)
    public void ByGrup(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        String format = uri.substring(uri.lastIndexOf(".") + 1);
        String id = request.getParameter("id");
        String nama = request.getParameter("nama");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("idGrup", id);
        parameters.put("namaGrup", nama == null || nama.equalsIgnoreCase("") ? "Semua" : nama);

        reportService.generateReport("ItemListByGrup", format, parameters, response, "item-by-grup");
    }

    @RequestMapping(value = "by-kategori*", method = RequestMethod.GET)
    public void ByKategori(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        String format = uri.substring(uri.lastIndexOf(".") + 1);
        String id = request.getParameter("id");
        String nama = request.getParameter("nama");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("idKategori", id);
        parameters.put("namaKategori", nama == null || nama.equalsIgnoreCase("") ? "Semua" : nama);

        reportService.generateReport("ItemListByKategori", format, parameters, response, "item-by-kategori");
    }
    
    @RequestMapping(value = "coba*", method = RequestMethod.GET)
    public void Coba(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        String format = uri.substring(uri.lastIndexOf(".") + 1);

        Map<String, Object> parameters = new HashMap<>();

        reportService.generateReport("coba", "pdf", parameters, response, "coba");
    }

    @RequestMapping(value = "report-kepegawaian*", method = RequestMethod.GET)
    public void Kepegawaian(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        String format = uri.substring(uri.lastIndexOf(".") + 1);
        String imgPath = context.getRealPath("/img") + System.getProperty("file.separator");
        System.out.println("uri : " + uri + ", context : " + context.getRealPath("/templates/jrxml/simpeg/"));
        String kode_instansi = request.getParameter("kode_instansi");
        String kode_unor = request.getParameter("kode_unor");
        String kode_status = request.getParameter("kode_status");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("imgPath", imgPath);
        parameters.put("kode_instansi", kode_instansi);
        parameters.put("kode_unor", kode_unor);
        parameters.put("kode_status", kode_status);

        reportService.generateReport("rep-kepegawaian", "pdf", parameters, response, "kepegawaian");
    }
    
    @RequestMapping(value = "report-permohonan-ijin*", method = RequestMethod.GET)
    public void PermohonanIjin(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        String format = uri.substring(uri.lastIndexOf(".") + 1);
        String imgPath = context.getRealPath("/img") + System.getProperty("file.separator");
        System.out.println("uri : " + uri + ", context : " + context.getRealPath("/templates/jrxml/simpeg/"));
        String id_ijin = request.getParameter("id_ijin");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("imgPath", imgPath);
        parameters.put("id_ijin", id_ijin);

        reportService.generateReport("pengajuan-cuti", "pdf", parameters, response, "pengajuan-cuti");
    }
    
    @RequestMapping(value = "report-pemberian-ijin*", method = RequestMethod.GET)
    public void PemberianIjin(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        String format = uri.substring(uri.lastIndexOf(".") + 1);
        String imgPath = context.getRealPath("/img") + System.getProperty("file.separator");
        System.out.println("uri : " + uri + ", context : " + context.getRealPath("/templates/jrxml/simpeg/"));
        String id_ijin = request.getParameter("id_ijin");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("imgPath", imgPath);
        parameters.put("id_ijin", id_ijin);

        reportService.generateReport("permintaan-pemberian-cuti", "pdf", parameters, response, "permintaan-pemberian-cuti");
    }
    
    @RequestMapping(value = "report-jumlah-cuti*", method = RequestMethod.GET)
    public void jumlahCutiPegawai(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        String format = uri.substring(uri.lastIndexOf(".") + 1);
        String imgPath = context.getRealPath("/img") + System.getProperty("file.separator");
        System.out.println("uri : " + uri + ", context : " + context.getRealPath("/templates/jrxml/simpeg/"));
        String tahun = request.getParameter("tahun");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("imgPath", imgPath);
        parameters.put("tahun", tahun);

        reportService.generateReport("rekap-cuti-tahunan", "pdf", parameters, response, "rekap-jumlah-cuti");
    }
}
