package com.supplyboost;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import java.io.IOException;

public class PdfPageRemover {

    public static void main(String[] args) {
        String sourcePdf = "cv_final.pdf";  // Path to your source PDF
        String destinationPdf = "cvdestination.pdf";  // Path for the output PDF
        int pageToRemove = 3;  // Page number to remove (1-based index)

        try {
            removePageFromPdf(sourcePdf, destinationPdf, pageToRemove);
            System.out.println("Page removed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removePageFromPdf(String src, String dest, int pageNumber) throws IOException {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(dest));

        if (pageNumber > 0 && pageNumber <= pdfDoc.getNumberOfPages()) {
            pdfDoc.removePage(pageNumber);
        } else {
            System.out.println("Page number out of range.");
        }

        pdfDoc.close();
    }
}
