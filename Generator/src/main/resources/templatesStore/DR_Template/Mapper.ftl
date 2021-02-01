<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${BasePackageName}${DaoPackageName}.${ClassName}Dao">

    <resultMap id="BaseResultMap" type="${BasePackageName}${EntityPackageName}.${ClassName}DO">
        ${ResultMap}
        ${Association}
        ${Collection}
    </resultMap>

    <sql id="BaseColumns">
        ${ColumnMap}
    </sql>

    <sql id="${EntityName}Joins">
        ${Joins}
    </sql>

    <select id="listByQuery" resultMap="BaseResultMap">
        SELECT
        <include refid="BaseColumns" />
        FROM ${TableName} <include refid="${EntityName}Joins" />
        <where>

        </where>
    </select>

    <select id="getByPrimaryKey" resultMap="BaseResultMap">
        SELECT
        <include refid="BaseColumns" />
        FROM ${TableName} <include refid="${EntityName}Joins" />
        <where>
            ${TableName}.${PrimaryKey} = ${WherePrimaryKey}
        </where>
    </select>

    <insert id="insert" parameterType="${BasePackageName}${EntityPackageName}.${ClassName}DO">
        INSERT INTO ${TableName}
        (<#list InsertProperties as propertie>${propertie.ColumnName}<#if propertie_has_next>, </#if></#list>)
        values
        (<#list InsertProperties as propertie>#${BigBracket}${propertie.PropertyName},jdbcType=${propertie.TypeName}}<#if propertie_has_next>, </#if></#list>)
    </insert>

    <insert id="insertSelective" parameterType="${BasePackageName}${EntityPackageName}.${ClassName}DO" >
        INSERT INTO ${TableName}
        <trim prefix="(" suffix=")" suffixOverrides="," >
        <#list InsertProperties as propertie>
            <if test="${propertie.PropertyName} != null" >
                ${propertie.ColumnName}<#if propertie_has_next>,</#if>
            </if>
        </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides="," >
        <#list InsertProperties as propertie>
            <if test="${propertie.PropertyName} != null" >
                #${BigBracket}${propertie.PropertyName},jdbcType=${propertie.TypeName}}<#if propertie_has_next>,</#if>
            </if>
        </#list>
        </trim>
    </insert>

    <update id="updateByPrimaryKeySelective" parameterType="${BasePackageName}${EntityPackageName}.${ClassName}DO" >
        UPDATE ${TableName}
        <set>
        <#list UpdateProperties as propertie>
            <if test="${propertie.PropertyName} != null" >
                ${propertie.ColumnName} = #${BigBracket}${propertie.PropertyName},jdbcType=${propertie.TypeName}}<#if propertie_has_next>,</#if>
            </if>
        </#list>
        </set>
        where ${TableName}.${PrimaryKey} = ${WherePrimaryKey}
    </update>

    <update id="updateByPrimaryKey" parameterType="${BasePackageName}${EntityPackageName}.${ClassName}DO" >
        UPDATE ${TableName}
        SET
        <#list UpdateProperties as propertie>
            <if test="${propertie.PropertyName} != null" >
                ${propertie.ColumnName} = #${BigBracket}${propertie.PropertyName},jdbcType=${propertie.TypeName}}<#if propertie_has_next>,</#if>
            </if>
        </#list>
        WHERE ${TableName}.${PrimaryKey} = ${WherePrimaryKey}
    </update>

    <update id="delete">
        DELETE FROM ${TableName}
        WHERE ${TableName}.${PrimaryKey} = ${WherePrimaryKey}
    </update>

    <#--    <insert id="insertBatch">-->
    <#--        INSERT INTO ${TableName}(-->
    <#--        ${InsertProperties}-->
    <#--        )-->
    <#--        VALUES-->
    <#--        <foreach collection ="list" item="${EntityName}" separator =",">-->
    <#--            (-->
    <#--            ${InsertBatchValues}-->
    <#--            )-->
    <#--        </foreach>-->
    <#--    </insert>-->

</mapper>