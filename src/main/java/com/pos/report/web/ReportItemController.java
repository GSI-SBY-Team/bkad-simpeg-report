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

    @RequestMapping(value = "percobaan*", method = RequestMethod.GET)
    public void Percobaan(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        String format = uri.substring(uri.lastIndexOf(".") + 1);
        String imgPath = context.getRealPath("/img") + System.getProperty("file.separator");
        System.out.println("uri : " + uri + ", context : " + context.getRealPath("/templates/jrxml/"));
//        String id = request.getParameter("id");
//        String nama = request.getParameter("nama");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("imgPath", imgPath);
//        parameters.put("idKategori", id);
//        parameters.put("namaKategori", nama == null || nama.equalsIgnoreCase("")? "Semua": nama);

        reportService.generateReport("coba", "pdf", parameters, response, "percobaan");
    }
}
