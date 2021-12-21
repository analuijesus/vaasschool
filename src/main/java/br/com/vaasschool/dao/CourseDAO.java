package br.com.vaasschool.dao;

import br.com.vaasschool.dto.CourseDto;
import br.com.vaasschool.dto.SubcategoryDto;
import br.com.vaasschool.model.Course;
import br.com.vaasschool.model.CourseVisibility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    private Connection connection;

    public CourseDAO(Connection connection) {
        this.connection = connection;
    }

    public void add(Course course) throws SQLException {
        String sqlInsert = """
                            insert into Course (name, code, estimated_time_to_finish, visibility, target_audience, 
                            instructor_name, summary, learned_skills, subcategory_id) values(?, ?, ?, ?, ?, ?, ?, ?, ?);
                        """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, course.getName());
            preparedStatement.setString(2, course.getCode());
            preparedStatement.setInt(3, course.getEstimatedTimeToFinish());
            preparedStatement.setString(4, String.valueOf(course.getVisibility()));
            preparedStatement.setString(5, course.getTargetAudience());
            preparedStatement.setString(6, course.getInstructorName());
            preparedStatement.setString(7, course.getSummary());
            preparedStatement.setString(8, course.getLearnedSkills());
            preparedStatement.setLong(9, course.getSubcategory().getId());

            preparedStatement.execute();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    course.setId(resultSet.getLong(1));
                    System.out.println("Novo curso inserido! Id: " + course.getId());
                }
            }
        }
    }

    public void delete(String code) throws SQLException {
        String sqlDelete = "delete from Course where code = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlDelete)) {
            preparedStatement.setString(1, code);

            preparedStatement.execute();
        }
    }

    public int updatesAllForPublicVisibility() throws SQLException {
        String sqlUpdate = "update Course set visibility = ? where visibility = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate)) {
            preparedStatement.setString(1, CourseVisibility.PUBLIC.name());
            preparedStatement.setString(2, CourseVisibility.PRIVATE.name());

            return preparedStatement.executeUpdate();
        }
    }

    public List<CourseDto> searchAllPublic() throws SQLException {
        List<CourseDto> courses = new ArrayList<>();

        String sqlSearch = """ 
                            select c.id as id, 
                                   c.name as name, 
                                   c.estimated_time_to_finish, 
                                   s.id as subcategory_id, 
                                   s.name as subcategory_name 
                            from Course c join Subcategory s on c.subcategory_id = s.id
                                          join Category ca on s.category_id = ca.id 
                            where c.visibility = 'PUBLIC';
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSearch)) {
            preparedStatement.execute();

            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    CourseDto courseDto = new CourseDto(resultSet.getInt("id"), resultSet.getString("name"),
                            resultSet.getInt("estimated_time_to_finish"),
                            new SubcategoryDto(resultSet.getInt("subcategory_id"), resultSet.getString("subcategory_name")));

                    courses.add(courseDto);
                }
            }
        }
        return courses;
    }
}
