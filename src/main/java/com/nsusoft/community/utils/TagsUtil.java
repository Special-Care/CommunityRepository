package com.nsusoft.community.utils;

import com.nsusoft.community.dto.TagsDto;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagsUtil {
    public static List<TagsDto> getListTags() {
        List<TagsDto> tagsDtoList = new ArrayList<>();
        TagsDto program = new TagsDto();
        program.setTopTag("开发语言");
        program.setSecondTags(Arrays.asList("javascript", "php", "css", "html", "html5", "java", "node.js", "python", "c++", "c", "golang", "objective-c", "typescript", "shell", "swift", "c#", "sass", "ruby", "bash", "less", "asp.net", "lua", "scala", "coffeescript", "actionscript", "rust", "erlang", "perl"));
        tagsDtoList.add(program);

        TagsDto framework = new TagsDto();
        framework.setTopTag("平台框架");
        framework.setSecondTags(Arrays.asList("laravel", "spring", "express", "django", "flask", "yii", "ruby-on-rails", "tornado", "koa", "struts"));
        tagsDtoList.add(framework);


        TagsDto server = new TagsDto();
        server.setTopTag("服务器");
        server.setSecondTags(Arrays.asList("linux", "nginx", "docker", "apache", "ubuntu", "centos", "缓存 tomcat", "负载均衡", "unix", "hadoop", "windows-server"));
        tagsDtoList.add(server);

        TagsDto db = new TagsDto();
        db.setTopTag("数据库");
        db.setSecondTags(Arrays.asList("mysql", "redis", "mongodb", "sql", "oracle", "nosql memcached", "sqlserver", "postgresql", "sqlite"));
        tagsDtoList.add(db);

        TagsDto tool = new TagsDto();
        tool.setTopTag("开发工具");
        tool.setSecondTags(Arrays.asList("git", "github", "visual-studio-code", "vim", "sublime-text", "xcode intellij-idea", "eclipse", "maven", "ide", "svn", "visual-studio", "atom emacs", "textmate", "hg"));
        tagsDtoList.add(tool);

        return tagsDtoList;
    }

    public static String filterInValid(String tags) {
        String[] strings = StringUtils.split(tags, ",");
        List<TagsDto> listTags = getListTags();

        List<String> collect = listTags.stream().flatMap(tag -> tag.getSecondTags().stream()).collect(Collectors.toList());
        String valids = Arrays.stream(strings).filter(temp -> !collect.contains(temp)).collect(Collectors.joining(","));
        return valids;
    }
}
