package Controller;

import Database.Data.PostsData;
import Database.Data.UsersData;
import Model.Content;
import Model.Post;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/uploadFile")
public class UploadFileController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFileController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        Post post = new Post();



        final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(fileItemFactory);

        // sets maximum size of upload file
        upload.setFileSizeMax(MAX_FILE_SIZE);

        try {
            List<FileItem> fileItems = upload.parseRequest(request);
            for (FileItem fileItem : fileItems) {
                if ("text".equals(fileItem.getFieldName())) {
                    String text = fileItem.getString();
                    post.content.setText(text);
                }
                if (!fileItem.isFormField() && fileItems.size() > 0) {
                    // xử lý file
                    String nameimg = fileItem.getName();
                    if (!nameimg.equals("")) {
                        File dir = new File("");
                        String dirUrl = dir.getAbsolutePath() + "/images/posts";
//                        dirUrl = request.getServletContext().getRealPath("") + "/images/posts";

                        dir = new File(dirUrl);
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }
                        String fileImg = dirUrl + File.separator + nameimg;
                        fileImg = dirUrl + "/" + nameimg; // Dien Test
                        File file = new File(fileImg);
                        if (fileItem.getSize() <= upload.getFileSizeMax()) {

                            try {
                                if ("images".equals(fileItem.getFieldName())) {
                                    post.content.getImages().add("images/posts/" + file.getName());
                                    fileItem.write(file);
                                }
                                if ("videos".equals(fileItem.getFieldName())) {
                                    post.content.getVideos().add("images/posts/" + file.getName());
                                    fileItem.write(file);
                                }
                            } catch (Exception e) {
                                System.out
                                        .println("CÓ LỖ TRONG QUÁ TRÌNH UPLOAD!");
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        post.setUser(UsersData.getUsers("0"));

        PostsData.insertPost(post);

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

}