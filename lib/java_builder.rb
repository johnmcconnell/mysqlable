require 'erb'

class JavaBuilder
  attr_reader :model
  def render(model)
    @model = model
    template = File.read(
      File.expand_path(File.join('..', '..', 'templates', 'java.erb'), __FILE__)
    )
    ERB.new(template, nil, '-').result get_binding
  end

  def class_name
    "JSON#{camel_case model.name}"
  end

  def fields
    model.fields.map do |field|
      { type: field_type(field),
        name: field_name(field) }
    end
  end

  def getters
    model.fields.map do |field|
      { method_name: getter_name(field),
        method_type: field_type(field),
        return_variable: "this.#{field_name field}"}
    end
  end

  def constructor_arguments
    fields.map { |f| "#{f[:type]} #{f[:name]}" }.join(', ')
  end

  def constructor_call_args(fields)
    fields.map { |f| field_name f }.join(', ')
  end

  def json_field_call_setter(field)
    if field.array?
      "JSONArray #{field_name field}Array = new JSONArray();\n" +
        "    for (#{model_type_of_array field.type} #{field_name_singular field} : #{field_name field}) {\n" +
           "      #{field_name field}Array.put(#{field_name_singular field}.toJSONObject());\n" +
        "    }\n    " +
        yield + "put(\"#{field.name}\", #{field_name field}Array);"
    elsif field.model?
      yield + "put(\"#{field.name}\", #{field_name field}.toJSONObject());"
    else
      yield + "put(\"#{field.name}\", #{field_name field});"
    end
  end

  def json_field_call_getter(field)
    "get#{field_type field}(\"#{field_name field}\")"
  end

  def constructor_declaration_args(fields)
    fields.map { |field| field_def field }.join(', ')
  end

  def constructor_set(field)
    "this.#{field_name field} = #{field_name field};"
  end

  def field_def(field)
    "#{field_type field} #{field_name field}"
  end

  def field_name(field)
    camel_back field.name
  end

  def field_name_singular(field)
    field_name(field)[0..-2]
  end

  def field_type(field)
    java_type(field.type)
  end

  def model_type_of_array(type_name)
    java_type /^Array\[(.*)\]$/.match(type_name)[1]
  end

  def java_type(type_name)
    if type_name =~ /^Array\[.*\]$/
      "List<#{model_type_of_array type_name}>"
    elsif type_name =~ /^Model\[.*\]$/
      camel_case /^Model\[(.*)\]$/.match(type_name)[1]
    else
      camel_case type_name
    end
  end

  def getter_name(field)
    "get#{camel_case field.name}"
  end

  def camel_case(text)
    return '' if text.nil?
    return text if text =~ /^[A-Z][a-zA-Z]+$/
    word = text.split(/_|\s+/).map { |part| part.capitalize }.join
    fail "invalid '#{word}' for camelcase" unless word =~ /^[A-Z][a-zA-Z]+$/
    word
  end

  def camel_back(text)
    return text if text =~ /^[A-Z][a-zA-Z]+$/
    first, remaining = * text.split(/_|\s+/)
    word = first + Array(remaining).map do |part|
      part.capitalize
    end.join
    fail "invalid '#{word}' for camelback" unless word =~ /^[a-z][a-zA-Z]+$/
    word
  end

  def get_binding()
    binding
  end
end
